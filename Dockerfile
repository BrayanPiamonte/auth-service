# ---- Etapa 1: compilar ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -B dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---- Etapa 2: imagen final (solo JRE, usuario sin privilegios) ----
FROM eclipse-temurin:17-jre
RUN useradd --system --uid 1001 appuser
WORKDIR /app
COPY --from=build /app/target/auth-service.jar app.jar
USER appuser
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
