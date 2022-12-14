package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.model.resource.ResourceName;
import com.mirae.smartfactory.domain.model.resource.ResourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResourceRepository {

    private final EntityManager em;

    public void save(ResourceName resourceName) {
        em.persist(resourceName);
    }

    public void remove(ResourceName resourceName) { em.remove(resourceName);}

    public Optional<ResourceName> findById(Long id) {
        return Optional.ofNullable(em.find(ResourceName.class, id));
    }

    public Optional<ResourceName> findByName(String materialName) {
        return Optional.ofNullable(em.createQuery(
                "select r from ResourceName r" +
                        " where r.materialName = :materialName", ResourceName.class
                ).setParameter("materialName", materialName).getResultStream().findFirst().orElse(null));
    }

    public List<ResourceName> findAllByResourceType(ResourceType resourceType) {
        return em.createQuery("select r from ResourceName r" + " where r.resourceType = :resourceType", ResourceName.class).setParameter("resourceType", resourceType).getResultList();
    }

    public List<ResourceName> findAll() {
        return em.createQuery(
                "select r from ResourceName r", ResourceName.class
        ).getResultList();
    }

}
