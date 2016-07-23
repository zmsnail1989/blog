package com.snail.architecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snail.architecture.dao.OrganizationDao;
import com.snail.architecture.entity.Organization;

/**
 * <p>User: Snail
 * <p>Date: 15-2-14
 * <p>Version: 1.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationDao organizationDao;

    
    public void createOrganization(Organization organization) {
         organizationDao.createOrganization(organization);
    }

    
    public void updateOrganization(Organization organization) {
         organizationDao.updateOrganization(organization);
    }

    
    public void deleteOrganization(Long organizationId) {
    	Organization organization = findOne(organizationId);
        organizationDao.deleteParentOrganization(organizationId);
        organizationDao.deleteChildOrganization(organization.makeSelfAsParentIds()+"%");
    }

    
    public Organization findOne(Long organizationId) {
        return organizationDao.findOne(organizationId);
    }

    
    public List<Organization> findAll() {
        return organizationDao.findAll();
    }

    
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        return organizationDao.findAllWithExclude(excludeOraganization);
    }

    
    public void move(Organization source, Organization target) {
        organizationDao.moveParent(target.getId(), target.getParentIds(), source.getId());
        organizationDao.moveChild(target.makeSelfAsParentIds(), source.makeSelfAsParentIds());
    }
}
