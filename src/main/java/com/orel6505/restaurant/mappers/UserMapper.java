package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.UserDto;
import com.orel6505.restaurant.models.User;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getAddress(),
                user.getEmail()
        );
    }

    public static User toEntity(UserDto dto) {
        User user = new User(
                dto.firstName(),
                dto.lastName(),
                dto.age() != null ? dto.age() : 0,
                dto.address(),
                dto.email()
        );
        user.setId(dto.id());
        return user;
    }
}