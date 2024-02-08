package ch.zhaw.psit4.turnier.persistence.service;


import ch.zhaw.psit4.turnier.model.User;
import ch.zhaw.psit4.turnier.persistence.repository.UserRepository;
import ch.zhaw.psit4.turnier.persistence.security.UserExistsException;
import ch.zhaw.psit4.turnier.persistence.security.UserNotLoggedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Integer> implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    private boolean userExists(String email) {
        User user = userRepository.findByUsername(email);
        return user != null;
    }

    public void createUser(User user) throws UserExistsException {
        if (userExists(user.getUsername())) {
            throw new UserExistsException("There is an account with that email adress: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getCurrentUser() throws UserNotLoggedInException {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new UserNotLoggedInException();
        }
    }

    @Override
    public CrudRepository<User, Integer> getRepository() {
        return userRepository;
    }
}
