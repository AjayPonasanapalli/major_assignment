package org.mavenproject.myfirstmavenproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody User newUser) {
        User existingUser = userService.findByEmail(newUser.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden, Account already exists");
        }
        userService.save(newUser);
        return ResponseEntity.ok("Account Creation Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        User existingUser = userService.findByEmail(loginUser.getEmail());
        if (existingUser == null || !existingUser.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Incorrect");
        }
        return ResponseEntity.ok("Login Successful");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam int userID) {
        User user = userService.findById(userID);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
        return ResponseEntity.ok(user);
    }
}
