package pl.sda.register.repository;

import org.springframework.stereotype.Repository;
import pl.sda.register.exception.DuplicatedUsernameException;
import pl.sda.register.exception.UserNotFoundException;
import pl.sda.register.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private Set<User> users = initializeUsers();

    private Set<User> initializeUsers() {
        return new HashSet<>(Arrays.asList(new User("login", "Captain", "Jack")));
    }

    public Set<String> findAllUserNames() {
        return users.stream().map(User::getUsername).collect(Collectors.toSet());
    }

    public User findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
    }

    public void addUser(User user) {
        users.stream()
                .filter(user1 -> user1.getUsername().equals(user.getUsername()))
                .findAny()
                .ifPresentOrElse(user1 -> throwException(user), () ->users.add(user));
    }

    private void throwException(User user) {
        throw new  DuplicatedUsernameException(
                "User with user name: " + user.getUsername() + "already exist");
    }

}
