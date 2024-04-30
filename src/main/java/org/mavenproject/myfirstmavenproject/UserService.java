package org.mavenproject.myfirstmavenproject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // Getters and setters
}
