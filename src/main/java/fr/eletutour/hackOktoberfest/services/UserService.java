package fr.eletutour.hackOktoberfest.services;

import fr.eletutour.hackOktoberfest.domain.user.AppUser;
import fr.eletutour.hackOktoberfest.exceptions.UserException;
import fr.eletutour.hackOktoberfest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> getAllUser() {
        return userRepository.findAll();
    }

    public AppUser getUserByUsername(String username) {
        var user = userRepository.findByUsername(username);
        if(Objects.isNull(user)) {
            throw new UserException(String.format("L'utilisateur %s n'existe pas", username), NOT_FOUND);
        }
        return user;
    }

    public AppUser createUser(AppUser user) {

        var u = userRepository.findByUsername(user.getUsername());
        if(u != null) {
            throw new UserException(String.format("L'utilisateur %s existe déjà", u.getUsername()), BAD_REQUEST);
        }

        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        var user = getUserByUsername(username);
        userRepository.delete(user);
    }

    public AppUser upateUser(AppUser newUser, String username) {
        var userToUpdate = getUserByUsername(username);

        userToUpdate.setUsername(newUser.getUsername());
        userToUpdate.setEmail(newUser.getEmail());
        userToUpdate.setPassword(newUser.getPassword());

        return userRepository.save(userToUpdate);
    }
}
