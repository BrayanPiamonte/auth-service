package co.edu.uptc.auth.token;

import co.edu.uptc.auth.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record TokenResponse(
        @Schema(description = "JWT firmado (HS256)") String accessToken,
        @Schema(example = "Bearer") String tokenType,
        @Schema(description = "Segundos de vigencia", example = "3600") long expiresIn,
        UserResponse user) {
}
