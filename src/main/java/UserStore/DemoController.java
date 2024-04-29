package UserStore;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DemoController(AppUserRepository repository,
                          PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/api/auth/signup")
    public AppUser register(@RequestBody RegistrationRequest request) {
        var user = new AppUser();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        //user.setAuthority(request.getAuthority());

        repository.save(user);

        return user;
    }
}