package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.resource.ResourceName;
import com.mirae.smartfactory.domain.resource.ResourceType;
import com.mirae.smartfactory.dto.*;
import com.mirae.smartfactory.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    @Transactional
    public OuterScrapNameListDto saveOuterScrapName(String newOuterScrapName) {
        resourceRepository.save(ResourceName.createResourceName(ResourceType.OUTER, newOuterScrapName));

        return findOuterScrapList();
    }

    @Transactional
    public SiNameListDto saveSiName(String newSiName) {
        resourceRepository.save(ResourceName.createResourceName(ResourceType.SI, newSiName));

        return findSiList();
    }

    @Transactional
    public IngredientNameListDto saveIngredientName(String newIngredientName) {
        resourceRepository.save(ResourceName.createResourceName(ResourceType.INGREDIENT, newIngredientName));

        return findIngredientList();
    }

    @Transactional
    public BusinessContactNameListDto saveBusinessContact(String newBusinessContactName) {
        resourceRepository.save(ResourceName.createResourceName(ResourceType.BUSINESS_CONTACT, newBusinessContactName));

        return findBusinessContactList();
    }

    public OuterScrapNameListDto findOuterScrapList() {
        List<ResourceName> allOuter = resourceRepository.findAllByResourceType(ResourceType.OUTER);
        OuterScrapNameListDto outerScrapNameListDto = new OuterScrapNameListDto();

        allOuter.stream().forEach(r -> {
            outerScrapNameListDto.addOuterScrap(r.getKrMaterialName());
        });

        return outerScrapNameListDto;
    }

    public SiNameListDto findSiList() {
        List<ResourceName> allSi = resourceRepository.findAllByResourceType(ResourceType.SI);
        SiNameListDto siNameListDto = new SiNameListDto();

        allSi.stream().forEach(r -> {
            siNameListDto.addSi(r.getKrMaterialName());
        });

        return siNameListDto;
    }

    public IngredientNameListDto findIngredientList() {
        List<ResourceName> allIngredientList = resourceRepository.findAllByResourceType(ResourceType.INGREDIENT);
        IngredientNameListDto ingredientNameListDto = new IngredientNameListDto();

        allIngredientList.stream().forEach(r -> {
            ingredientNameListDto.addIngredient(r.getKrMaterialName());
        });

        return ingredientNameListDto;
    }

    public BusinessContactNameListDto findBusinessContactList() {
        List<ResourceName> allBusinessContact = resourceRepository.findAllByResourceType(ResourceType.BUSINESS_CONTACT);
        BusinessContactNameListDto businessContactNameListDto = new BusinessContactNameListDto();

        allBusinessContact.stream().forEach(r -> {
            businessContactNameListDto.addBusinessContact(r.getKrMaterialName());
        });

        return businessContactNameListDto;
    }

    public ResourceNameDto findAllResources() {
        List<ResourceName> allResources = resourceRepository.findAll();

        OuterScrapNameListDto outerScrapNameListDto = new OuterScrapNameListDto();
        SiNameListDto siNameListDto = new SiNameListDto();
        IngredientNameListDto ingredientNameListDto = new IngredientNameListDto();
        BusinessContactNameListDto businessContactNameListDto = new BusinessContactNameListDto();

        allResources.stream()
                .filter(r -> (r.getResourceType() == ResourceType.OUTER))
                .forEach(o -> {
                    System.out.println(o.getKrMaterialName());
                    System.out.println(o.getResourceType());
                    outerScrapNameListDto.addOuterScrap(o.getKrMaterialName());
                });

        allResources.stream()
                .filter(r -> r.getResourceType() == ResourceType.SI)
                .forEach(s -> siNameListDto.addSi(s.getKrMaterialName()));

        allResources.stream()
                .filter(r -> r.getResourceType() == ResourceType.INGREDIENT)
                .forEach(i -> ingredientNameListDto.addIngredient(i.getKrMaterialName()));

        allResources.stream()
                .filter(r -> r.getResourceType() == ResourceType.BUSINESS_CONTACT)
                .forEach(b -> businessContactNameListDto.addBusinessContact(b.getKrMaterialName()));

        return new ResourceNameDto(outerScrapNameListDto, siNameListDto, ingredientNameListDto, businessContactNameListDto);
    }

}
