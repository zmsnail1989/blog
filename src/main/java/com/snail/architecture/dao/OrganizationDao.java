package com.snail.architecture.dao;

import java.util.List;

import com.snail.architecture.entity.Organization;

/**
 * <p>Organization: Snail
 * <p>Date: 16-1-11
 * <p>Version: 1.0
 */
public interface OrganizationDao {

    public int createOrganization(Organization organization);
    public int updateOrganization(Organization organization);
    public void deleteParentOrganization(Long organizationId);
    public void deleteChildOrganization(String selfAsParentIds);
    public Organization findOne(Long organizationId);
    public List<Organization> findAll();
    public List<Organization> findAllWithExclude(Organization excludeOraganization);
    public void moveParent(Long targetId,String targetParentIds,Long sourceId);
    public void moveChild(String targetSelfAsParentIds,String sourceSelfAsParentIds);
}
