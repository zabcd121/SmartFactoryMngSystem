package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.model.resource.ResourceName;
import com.mirae.smartfactory.domain.model.resource.ResourceType;
import com.mirae.smartfactory.dto.resource.*;
import com.mirae.smartfactory.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    @Transactional
    public ResourceNameListDto saveOuterScrapName(String newOuterScrapName) {
        resourceRepository.save(ResourceName.createResourceName(ResourceType.OUTER, newOuterScrapName));

        return findOuterScrapList();
    }

    @Transactional
    public ResourceNameListDto saveSiName(String newSiName) {
        resourceRepository.save(ResourceName.createResourceName(ResourceType.SI, newSiName));

        return findSiList();
    }

    @Transactional
    public ResourceNameListDto saveIngredientName(String newIngredientName) {
        resourceRepository.save(ResourceName.createResourceName(ResourceType.INGREDIENT, newIngredientName));

        return findIngredientList();
    }

    @Transactional
    public ResourceNameListDto saveBusinessContact(String newBusinessContactName) {
        resourceRepository.save(ResourceName.createResourceName(ResourceType.BUSINESS_CONTACT, newBusinessContactName));

        return findBusinessContactList();
    }

    @Transactional
    public void updateResourceName(Long id, String newOuterScrapName) {
        Optional<ResourceName> resourceName = resourceRepository.findById(id);

        resourceName.get().changeMaterialName(newOuterScrapName);
    }

    public ResourceNameListDto findOuterScrapList() {
        List<ResourceName> allOuter = resourceRepository.findAllByResourceType(ResourceType.OUTER);
        ResourceNameListDto outerScrapNameListDto = new ResourceNameListDto();

        allOuter.stream().forEach(r -> {
            outerScrapNameListDto.addResource(r.getResourceNameId(), r.getMaterialName());
        });

        return outerScrapNameListDto;
    }

    public ResourceNameListDto findSiList() {
        List<ResourceName> allSi = resourceRepository.findAllByResourceType(ResourceType.SI);
        ResourceNameListDto siNameListDto = new ResourceNameListDto();

        allSi.stream().forEach(r -> {
            siNameListDto.addResource(r.getResourceNameId(), r.getMaterialName());
        });

        return siNameListDto;
    }

    public ResourceNameListDto findIngredientList() {
        List<ResourceName> allIngredientList = resourceRepository.findAllByResourceType(ResourceType.INGREDIENT);
        ResourceNameListDto ingredientNameListDto = new ResourceNameListDto();

        allIngredientList.stream().forEach(r -> {
            ingredientNameListDto.addResource(r.getResourceNameId(), r.getMaterialName());
        });

        return ingredientNameListDto;
    }

    public ResourceNameListDto findBusinessContactList() {
        List<ResourceName> allBusinessContact = resourceRepository.findAllByResourceType(ResourceType.BUSINESS_CONTACT);
        ResourceNameListDto businessContactNameListDto = new ResourceNameListDto();

        allBusinessContact.stream().forEach(r -> {
            businessContactNameListDto.addResource(r.getResourceNameId(), r.getMaterialName());
        });

        return businessContactNameListDto;
    }

    public TotalResourceNameDto findAllResources() {
        List<ResourceName> allResources = resourceRepository.findAll();

        ResourceNameListDto outerScrapNameListDto = new ResourceNameListDto();
        ResourceNameListDto siNameListDto = new ResourceNameListDto();
        ResourceNameListDto ingredientNameListDto = new ResourceNameListDto();
        ResourceNameListDto businessContactNameListDto = new ResourceNameListDto();

        allResources.stream()
                .filter(r -> (r.getResourceType() == ResourceType.OUTER))
                .forEach(o -> {
                    System.out.println(o.getMaterialName());
                    System.out.println(o.getResourceType());
                    outerScrapNameListDto.addResource(o.getResourceNameId(), o.getMaterialName());
                });

        allResources.stream()
                .filter(r -> r.getResourceType() == ResourceType.SI)
                .forEach(s -> siNameListDto.addResource(s.getResourceNameId(), s.getMaterialName()));

        allResources.stream()
                .filter(r -> r.getResourceType() == ResourceType.INGREDIENT)
                .forEach(i -> ingredientNameListDto.addResource(i.getResourceNameId(), i.getMaterialName()));

        allResources.stream()
                .filter(r -> r.getResourceType() == ResourceType.BUSINESS_CONTACT)
                .forEach(b -> businessContactNameListDto.addResource(b.getResourceNameId(), b.getMaterialName()));

        return new TotalResourceNameDto(outerScrapNameListDto, siNameListDto, ingredientNameListDto, businessContactNameListDto);
    }

    @Transactional
    public void removeResourceName(Long resourceNameId) {
        Optional<ResourceName> resourceName = resourceRepository.findById(resourceNameId);
        resourceRepository.remove(resourceName.get());
    }
}
