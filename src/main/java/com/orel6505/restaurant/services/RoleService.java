package com.orel6505.restaurant.services;

import com.orel6505.restaurant.dto.RoleDto;
import com.orel6505.restaurant.mappers.RoleMapper;
import com.orel6505.restaurant.models.Role;
import com.orel6505.restaurant.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
            .map(roleMapper::toDto).toList();
    }

    public RoleDto getRoleById(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(roleMapper::toDto).orElse(null);
    }

    public RoleDto getRoleByName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        return role.map(roleMapper::toDto).orElse(null);
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole);
    }

    public RoleDto updateRole(Integer id, RoleDto roleDto) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if (existingRole.isPresent()) {
            Role role = existingRole.get();
            role.setRoleName(roleDto.getRoleName());
            role.setRoleDescription(roleDto.getRoleDescription());
            Role updatedRole = roleRepository.save(role);
            return roleMapper.toDto(updatedRole);
        }
        return null;
    }

    public boolean deleteRole(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
