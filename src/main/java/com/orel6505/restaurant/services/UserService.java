package com.orel6505.restaurant.services;

import com.orel6505.restaurant.models.User;
import com.orel6505.restaurant.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public List<User> searchByName(String name) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    public User create(User user) {
        user.setId(0);
        return userRepository.save(user);
    }

    public User update(int id, User updated) {
        User existing = getById(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setAge(updated.getAge());
        existing.setAddress(updated.getAddress());
        existing.setEmail(updated.getEmail());
        return userRepository.save(existing);
    }

    public void delete(int id) {
        User existing = getById(id);
        userRepository.delete(existing);
    }
}