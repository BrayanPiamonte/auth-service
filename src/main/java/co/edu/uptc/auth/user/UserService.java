package co.edu.uptc.auth.user;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    /** Hash de relleno: se compara igual cuando el usuario no existe, para no revelarlo por tiempo de respuesta. */
    private final String dummyHash;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
        this.dummyHash = encoder.encode("relleno-contra-timing");
    }

    /** Usuario público si las credenciales son correctas; vacío si no. */
    public Optional<UserResponse> verify(String username, String rawPassword) {
        Optional<StoredUser> user = repository.findByUsername(username);
        boolean matches = encoder.matches(rawPassword, user.map(StoredUser::passwordHash).orElse(dummyHash));
        return user.filter(u -> matches).map(UserService::toPublic);
    }

    /** Crea un usuario con rol USER; vacío si el nombre ya existe. */
    public Optional<UserResponse> create(String username, String rawPassword) {
        return insert(username, rawPassword, Role.USER);
    }

    /** Crea el ADMIN inicial si no existe (no sobrescribe uno existente). */
    public void ensureAdmin(String username, String rawPassword) {
        if (repository.findByUsername(username).isEmpty()) {
            insert(username, rawPassword, Role.ADMIN);
        }
    }

    private Optional<UserResponse> insert(String username, String rawPassword, Role role) {
        boolean created = repository.insert(username, encoder.encode(rawPassword), role);
        return created ? Optional.of(new UserResponse(username, role.name())) : Optional.empty();
    }

    private static UserResponse toPublic(StoredUser user) {
        return new UserResponse(user.username(), user.role().name());
    }
}
