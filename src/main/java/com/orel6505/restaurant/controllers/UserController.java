package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.UserDto;
import com.orel6505.restaurant.mappers.UserMapper;
import com.orel6505.restaurant.models.User;
import com.orel6505.restaurant.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll().stream().map(UserMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") int id) {
        return UserMapper.toDto(userService.getById(id));
    }

    @GetMapping("/search")
    public List<UserDto> searchByName(@RequestParam("name") String name) {
        return userService.searchByName(name).stream().map(UserMapper::toDto).toList();
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        User created = userService.create(UserMapper.toEntity(dto));
        return UserMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") int id, @RequestBody UserDto dto) {
        User updated = userService.update(id, UserMapper.toEntity(dto));
        return UserMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }
}