package com.orel6505.restaurant.dto;

public class RoleDto {
    private Integer id;
    private String roleName;
    private String roleDescription;

    public RoleDto() {
    }

    public RoleDto(Integer id, String roleName, String roleDescription) {
        this.id = id;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
