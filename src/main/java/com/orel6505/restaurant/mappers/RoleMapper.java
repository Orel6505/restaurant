package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.RoleDto;
import com.orel6505.restaurant.models.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDto toDto(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleDto(
            role.getId(),
            role.getRoleName(),
            role.getRoleDescription()
        );
    }

    public Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        Role role = new Role(
            roleDto.getRoleName(),
            roleDto.getRoleDescription()
        );
        role.setId(roleDto.getId());
        return role;
    }
}
