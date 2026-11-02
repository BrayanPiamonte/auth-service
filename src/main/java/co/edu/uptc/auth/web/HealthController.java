package co.edu.uptc.auth.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Salud", description = "Estado del módulo")
public class HealthController {

    @GetMapping("/health")
    @Operation(summary = "Chequeo de disponibilidad (health check de la plataforma)")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }
}
