package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.UserDto;
import com.orel6505.restaurant.mappers.UserMapper;
import com.orel6505.restaurant.models.User;
import com.orel6505.restaurant.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Users", description = "User Management API")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @Operation(summary = "Get all users", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<UserDto> getAll() {
        return userService.getAll().stream().map(UserMapper::toDto).toList();
    }

    @GetMapping("/search")
    @Operation(summary = "Search users by name", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<UserDto> searchByName(@RequestParam("name") String name) {
        return userService.searchByName(name).stream().map(UserMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public UserDto getById(@PathVariable("id") int id) {
        return UserMapper.toDto(userService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create new user", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto create(@RequestBody UserDto dto) {
        User user = new User(dto.firstName(), dto.lastName(), 
            dto.age() != null ? dto.age() : 0,
            dto.address(), dto.email(), 
            passwordEncoder.encode(dto.password()));
        User created = userService.create(user);
        return UserMapper.toDto(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto update(@PathVariable("id") int id, @RequestBody UserDto dto) {
        User user = userService.getById(id);
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        if (dto.age() != null) user.setAge(dto.age());
        user.setAddress(dto.address());
        user.setEmail(dto.email());
        if (dto.password() != null && !dto.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }
        User updated = userService.update(id, user);
        return UserMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }
}