package pl.sda.register.service;

import org.springframework.stereotype.Service;
import pl.sda.register.model.User;
import pl.sda.register.repository.UserRepository;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<String> findAllUserNames(String firstName) {
        return userRepository.findAllUserNames(firstName);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }


}
