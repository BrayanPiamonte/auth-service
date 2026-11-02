package co.edu.uptc.auth.error;

public class UsernameTakenException extends RuntimeException {

    public UsernameTakenException() {
        super("Ese nombre de usuario ya existe");
    }
}
