package com.snail.architecture.dao;

import java.util.List;

import com.snail.architecture.entity.Role;

/**
 * <p>User: Snail
 * <p>Date: 16-1-11
 * <p>Version: 1.0
 */
public interface RoleDao {

    public int createRole(Role role);
    public int updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();
}
