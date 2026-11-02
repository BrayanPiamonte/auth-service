package co.edu.uptc.auth.user;

import co.edu.uptc.auth.config.AuthProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** Al arrancar, crea el usuario ADMIN inicial a partir de ADMIN_USERNAME / ADMIN_PASSWORD (si no existe). */
@Component
public class AdminSeeder implements ApplicationRunner {

    private final UserService users;
    private final AuthProperties props;

    public AdminSeeder(UserService users, AuthProperties props) {
        this.users = users;
        this.props = props;
    }

    @Override
    public void run(ApplicationArguments args) {
        users.ensureAdmin(props.admin().username(), props.admin().password());
    }
}
