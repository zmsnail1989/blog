package com.snail.architecture.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snail.architecture.dao.RoleDao;
import com.snail.architecture.entity.Role;

/**
 * <p>User: Snail
 * <p>Date: 16-1-11
 * <p>Version: 1.0
 */
@Service

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceService resourceService;

    public void createRole(Role role) {
    	role.setResStr(role.getResourceIdsStr());
        roleDao.createRole(role);
    }

    public void updateRole(Role role) {
    	role.setResStr(role.getResourceIdsStr());
        roleDao.updateRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    
    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}
