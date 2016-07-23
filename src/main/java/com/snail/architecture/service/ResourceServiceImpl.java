package com.snail.architecture.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.snail.architecture.Constants;
import com.snail.architecture.dao.ResourceDao;
import com.snail.architecture.entity.MenuVo;
import com.snail.architecture.entity.Resource;
import com.snail.architecture.entity.Resource.ResourceType;

/**
 * <p>User: Snail
 * <p>Date: 15-2-14
 * <p>Version: 1.0
 */
@Service

public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private SequenceService sequenceService;
    @Autowired
    private ResourceDao resourceDao;

    
    public void createResource(Resource resource) {
    	resource.setAvailable(true);
    	if (resource.getIsRoot()==0) {
    		 resourceDao.createResource(resource);
		}else {
			Long key = sequenceService.getNextvalSequence("sys_resource");
			resource.setTypeName(resource.getType().name());
			resource.setParentId(key);
			resource.setParentIds(key+"/");
			resourceDao.createResource(resource);
		}
    }

    
    public void updateResource(Resource resource) {
        resourceDao.updateResource(resource);
    }

    
    public void deleteResource(Long resourceId) {
    	Resource resource = findOne(resourceId);
        resourceDao.deleteParentResource(resourceId);
        resourceDao.deleteChildResource(resource.makeSelfAsParentIds()+"%");
    }

    
    public Resource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    public List<Resource> findMenu(Long type) {
        return resourceDao.findMenu(type);
    }

    
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    
	public List<MenuVo> findMenus(Set<String> permissions) {
		List<MenuVo> menus = new ArrayList<MenuVo>();
		List<Resource> parentMenus = findMenu(Constants.PARENT_MENU);
		List<Resource> childMenus = findMenu(Constants.CHILD_MENU);
		for (Resource parentMenu : parentMenus) {
			List<MenuVo> subMenu = new ArrayList<>();
			for (Resource resource : childMenus) {
				if (resource.getType() != Resource.ResourceType.menu) {
					continue;
				}
				if (!hasPermission(permissions, resource)) {
					continue;
				}
				if (resource.getParentId() == parentMenu.getId()) {
					MenuVo sub = new MenuVo();
					sub.setId(resource.getId());
					sub.setName(resource.getName());
					sub.setUrl(resource.getUrl());
					subMenu.add(sub);
				}
			}
			if (subMenu.size()>0) {
				MenuVo parent = new MenuVo();
				parent.setId(parentMenu.getId());
				parent.setName(parentMenu.getName());
				parent.setSubmenu(subMenu);
				menus.add(parent);
			}
		}
		return menus;
	}

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
