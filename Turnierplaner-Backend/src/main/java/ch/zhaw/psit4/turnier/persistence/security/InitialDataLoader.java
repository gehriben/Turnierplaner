package ch.zhaw.psit4.turnier.persistence.security;

import ch.zhaw.psit4.turnier.model.User;
import ch.zhaw.psit4.turnier.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @CrossOrigin
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup){
            return;
        }
        User u = createUserIfNotFound("admin@example.com", passwordEncoder.encode("secret"), true);

        alreadySetup = true;
    }

    @Transactional
    public User createUserIfNotFound(String name, String password, Boolean admin) {
        User user = userRepository.findByUsername(name);
        if (user == null) {
            user = new User();
            user.setUsername(name);
            user.setPassword(password);
            user.setAdmin(admin);
            userRepository.save(user);
        }
        return user;
    }
}
