package com.snail.architecture.service;

import com.snail.architecture.entity.Organization;

import java.util.List;

/**
 * <p>User: Snail
 * <p>Date: 16-1-11
 * <p>Version: 1.0
 */
public interface OrganizationService {


    public void createOrganization(Organization organization);
    public void updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
