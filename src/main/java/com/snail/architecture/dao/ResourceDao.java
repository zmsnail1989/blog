package com.snail.architecture.dao;

import com.snail.architecture.entity.Resource;

import java.util.List;

/**
 * <p>Resource: Snail
 * <p>Date: 16-1-11
 * <p>Version: 1.0
 */
public interface ResourceDao {

    public int createParentResource(Resource resource);
    public int createResource(Resource resource);
    public int updateResource(Resource resource);
    public int deleteParentResource(Long resourceId);
    public int deleteChildResource(String selfAsParentIds);
    Resource findOne(Long resourceId);
    List<Resource> findAll();
    List<Resource> findMenu(Long type);
}
