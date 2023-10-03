package fr.eletutour.hackOktoberfest.controller;

import fr.eletutour.hackOktoberfest.domain.user.AppUser;
import fr.eletutour.hackOktoberfest.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<AppUser> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{username}")
    public AppUser getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user){
        return userService.createUser(user);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @PutMapping("/{username}")
    public AppUser updateUser(@RequestBody AppUser newUser, @PathVariable String username) {
        return userService.upateUser(newUser, username);
    }

}
