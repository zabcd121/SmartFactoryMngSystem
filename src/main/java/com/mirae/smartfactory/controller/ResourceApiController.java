package com.mirae.smartfactory.controller;

import com.mirae.smartfactory.consts.ConditionCode;
import com.mirae.smartfactory.dto.resource.*;
import com.mirae.smartfactory.dto.result.SuccessNoResult;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.application.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.mirae.smartfactory.consts.ConditionCode.*;


@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceApiController {

    private final ResourceService resourceService;

    @PostMapping("/outerscrap")
    public SuccessResult<ResourceNameListDto> outerScrapAdd(@Validated @RequestBody SaveAndUpdateResourceNameDto saveResourceNameDto) {
        ResourceNameListDto outerScrapNameListDto = resourceService.saveOuterScrapName(saveResourceNameDto.getResourceName());
        return new SuccessResult<ResourceNameListDto>(OUTERSCRAP_ADD_SUCCESS.getCode(), OUTERSCRAP_ADD_SUCCESS.getCode(), outerScrapNameListDto);
    }

    @PutMapping("/outerscrap/{id}")
    public SuccessNoResult outerScrapUpdate(@PathVariable("id") Long resourceNameId, @Validated @RequestBody SaveAndUpdateResourceNameDto updateResourceNameDto) {
        resourceService.updateResourceName(resourceNameId, updateResourceNameDto.getResourceName());

        return new SuccessNoResult(RESOURCE_UPDATE_SUCCESS.getCode(), RESOURCE_UPDATE_SUCCESS.getMessage());
    }

    @PostMapping("/si")
    public SuccessResult<ResourceNameListDto> siAdd(@Validated @RequestBody SaveAndUpdateResourceNameDto saveResourceNameDto) {
        ResourceNameListDto siNameListDto = resourceService.saveSiName(saveResourceNameDto.getResourceName());
        return new SuccessResult<ResourceNameListDto>(SI_ADD_SUCCESS.getCode(), SI_ADD_SUCCESS.getMessage(), siNameListDto);
    }

    @PutMapping("/si/{id}")
    public SuccessNoResult siUpdate(@PathVariable("id") Long resourceNameId, @Validated @RequestBody SaveAndUpdateResourceNameDto updateResourceNameDto) {
        resourceService.updateResourceName(resourceNameId, updateResourceNameDto.getResourceName());

        return new SuccessNoResult(RESOURCE_UPDATE_SUCCESS.getCode(), RESOURCE_UPDATE_SUCCESS.getMessage());
    }

    @PostMapping("/ingredient")
    public SuccessResult<ResourceNameListDto> ingredientAdd(@Validated @RequestBody SaveAndUpdateResourceNameDto saveResourceNameDto) {
        ResourceNameListDto ingredientNameListDto = resourceService.saveIngredientName(saveResourceNameDto.getResourceName());
        return new SuccessResult<ResourceNameListDto>(INGREDIENT_ADD_SUCCESS.getCode(), INGREDIENT_ADD_SUCCESS.getMessage(), ingredientNameListDto);
    }

    @PutMapping("/ingredient/{id}")
    public SuccessNoResult ingredientUpdate(@PathVariable("id") Long resourceNameId, @Validated @RequestBody SaveAndUpdateResourceNameDto updateResourceNameDto) {
        resourceService.updateResourceName(resourceNameId, updateResourceNameDto.getResourceName());

        return new SuccessNoResult(RESOURCE_UPDATE_SUCCESS.getCode(), RESOURCE_UPDATE_SUCCESS.getMessage());
    }

    @PostMapping("/contact")
    public SuccessResult<ResourceNameListDto> businessContactAdd(@Validated @RequestBody SaveAndUpdateResourceNameDto saveResourceNameDto) {
        ResourceNameListDto businessContactNameListDto = resourceService.saveBusinessContact(saveResourceNameDto.getResourceName());
        return new SuccessResult<ResourceNameListDto>(CONTACT_ADD_SUCCESS.getCode(), CONTACT_ADD_SUCCESS.getMessage(), businessContactNameListDto);
    }

    @PutMapping("/contact/{id}")
    public SuccessNoResult businessContactUpdate(@PathVariable("id") Long resourceNameId, @Validated @RequestBody SaveAndUpdateResourceNameDto updateResourceNameDto) {
        resourceService.updateResourceName(resourceNameId, updateResourceNameDto.getResourceName());

        return new SuccessNoResult(RESOURCE_UPDATE_SUCCESS.getCode(), RESOURCE_UPDATE_SUCCESS.getMessage());
    }

    @GetMapping
    public SuccessResult<TotalResourceNameDto> totalResourcesSearch() {
        TotalResourceNameDto totalResources = resourceService.findAllResources();
        return new SuccessResult<TotalResourceNameDto>(RESOURCE_TOTAL_SEARCH_SUCCESS.getCode(), RESOURCE_TOTAL_SEARCH_SUCCESS.getMessage(), totalResources);

    }

    @DeleteMapping("/{id}")
    public SuccessNoResult resourceDelete(@PathVariable("id") Long resourceNameId) {
        resourceService.removeResourceName(resourceNameId);

        return new SuccessNoResult(RESOURCE_REMOVE_SUCCESS.getCode(), RESOURCE_REMOVE_SUCCESS.getMessage());
    }





}
