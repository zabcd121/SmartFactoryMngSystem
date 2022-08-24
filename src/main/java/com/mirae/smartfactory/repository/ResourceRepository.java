package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.resource.ResourceName;
import com.mirae.smartfactory.domain.resource.ResourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResourceRepository {

    private final EntityManager em;

    public void save(ResourceName resourceName) {
        em.persist(resourceName);
    }

//    public void save_si(ResourceName siName) {
//        em.persist(siName);
//    }
//
//    public void save_ingredient(ResourceName ingredientName) {
//        em.persist(ingredientName);
//    }
//
//    public void save_businessContact(ResourceName businessContactName) {
//        em.persist(businessContactName);
//    }

    public List<ResourceName> findAllByResourceType(ResourceType resourceType) {
        return em.createQuery("select r from ResourceName r" + " where r.resourceType = :resourceType", ResourceName.class).setParameter("resourceType", resourceType).getResultList();
    }

//    public List<ResourceName> findAll_si() {
//        return em.createQuery(
//                        "select r from ResourceName r" +
//                                " where r.resourceType = 'SI'", ResourceName.class)
//                .getResultList();
//    }
//
//    public List<ResourceName> findAll_ingredient() {
//        return em.createQuery(
//                        "select r from ResourceName r" +
//                                " where r.resourceType = 'INGREDIENT'", ResourceName.class)
//                .getResultList();
//    }
//
//    public List<ResourceName> findAll_businessContact() {
//        return em.createQuery(
//                        "select r from ResourceName r" +
//                                " where r.resourceType = 'BUSINESS_CONTACT'", ResourceName.class)
//                .getResultList();
//    }

    public List<ResourceName> findAll() {
        return em.createQuery(
                "select r from ResourceName r", ResourceName.class
        ).getResultList();
    }

}
