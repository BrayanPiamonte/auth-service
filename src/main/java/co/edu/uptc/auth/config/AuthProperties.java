package co.edu.uptc.auth.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

/** Configuración del módulo (prefijo "auth"). Si falta un secreto, la app no arranca y dice cuál. */
@Validated
@ConfigurationProperties(prefix = "auth")
public record AuthProperties(
        @Valid @DefaultValue Jwt jwt,
        @Valid @DefaultValue Admin admin) {

    public record Jwt(
            @NotBlank(message = "es obligatorio (variable JWT_SECRET)")
            @Size(min = 32, message = "debe tener al menos 32 caracteres")
            String secret,
            @DefaultValue("1h") Duration expiresIn,
            @DefaultValue("api-gateway-uptc") String issuer,
            @DefaultValue("sistema-academico") String audience) {
    }

    public record Admin(
            @DefaultValue("admin") @Size(min = 3) String username,
            @NotBlank(message = "es obligatorio (variable ADMIN_PASSWORD)")
            @Size(min = 8, message = "debe tener al menos 8 caracteres")
            String password) {
    }
}
