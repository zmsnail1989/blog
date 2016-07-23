package com.snail.architecture.service;

import java.util.List;
import java.util.Set;

import com.snail.architecture.entity.MenuVo;
import com.snail.architecture.entity.Resource;

/**
 * <p>User: Snail
 * <p>Date: 16-1-11
 * <p>Version: 1.0
 */
public interface ResourceService {


    public void createResource(Resource resource);
    public void updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();
    
    List<Resource> findMenu(Long type);

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<MenuVo> findMenus(Set<String> permissions);
}
