package com.art.crud.dao;



import com.art.crud.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRole();
    Role getRoleById(Long id);
    Role getRoleByName(String role);
    void addRole(Role role);
    void deleteRole(Role role);
    void updateRole(Role role);
}
