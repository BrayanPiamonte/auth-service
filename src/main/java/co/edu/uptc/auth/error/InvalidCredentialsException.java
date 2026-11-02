package co.edu.uptc.auth.error;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Usuario o contraseña incorrectos");
    }
}
