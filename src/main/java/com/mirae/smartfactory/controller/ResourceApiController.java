package com.mirae.smartfactory.controller;

import com.mirae.smartfactory.dto.resource.*;
import com.mirae.smartfactory.dto.result.SuccessNoResult;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.mirae.smartfactory.consts.DomainConditionCode.*;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceApiController {

    private final ResourceService resourceService;

    @PostMapping("/outerscrap")
    public SuccessResult<ResourceNameListDto> outerScrapAdd(@Validated @RequestBody SaveAndUpdateResourceNameDto saveResourceNameDto) {
        ResourceNameListDto outerScrapNameListDto = resourceService.saveOuterScrapName(saveResourceNameDto.getResourceName());
        return new SuccessResult<ResourceNameListDto>(OUTERSCRAP_ADD_SUCCESS, "외부스크랩 추가에 성공했습니다.", outerScrapNameListDto);
    }

    @PutMapping("/outerscrap/{id}")
    public SuccessNoResult outerScrapUpdate(@PathVariable("id") Long resourceNameId, @Validated @RequestBody SaveAndUpdateResourceNameDto updateResourceNameDto) {
        resourceService.updateResourceName(resourceNameId, updateResourceNameDto.getResourceName());

        return new SuccessNoResult(OUTERSCRAP_ADD_SUCCESS, "외부스크랩 수정에 성공했습니다.");
    }

    @PostMapping("/si")
    public SuccessResult<ResourceNameListDto> siAdd(@Validated @RequestBody SaveAndUpdateResourceNameDto saveResourceNameDto) {
        ResourceNameListDto siNameListDto = resourceService.saveSiName(saveResourceNameDto.getResourceName());
        return new SuccessResult<ResourceNameListDto>(SI_ADD_SUCCESS, "Si 원소 추가에 성공했습니다.", siNameListDto);
    }

    @PutMapping("/si/{id}")
    public SuccessNoResult siUpdate(@PathVariable("id") Long resourceNameId, @Validated @RequestBody SaveAndUpdateResourceNameDto updateResourceNameDto) {
        resourceService.updateResourceName(resourceNameId, updateResourceNameDto.getResourceName());

        return new SuccessNoResult(SI_ADD_SUCCESS, "Si 원소 수정에 성공했습니다.");
    }

    @PostMapping("/ingredient")
    public SuccessResult<ResourceNameListDto> ingredientAdd(@Validated @RequestBody SaveAndUpdateResourceNameDto saveResourceNameDto) {
        ResourceNameListDto ingredientNameListDto = resourceService.saveIngredientName(saveResourceNameDto.getResourceName());
        return new SuccessResult<ResourceNameListDto>(INGREDIENT_ADD_SUCCESS, "성분 분석 수치 원소 추가에 성공했습니다.", ingredientNameListDto);
    }

    @PutMapping("/ingredient/{id}")
    public SuccessNoResult ingredientUpdate(@PathVariable("id") Long resourceNameId, @Validated @RequestBody SaveAndUpdateResourceNameDto updateResourceNameDto) {
        resourceService.updateResourceName(resourceNameId, updateResourceNameDto.getResourceName());

        return new SuccessNoResult(INGREDIENT_ADD_SUCCESS, "성분 분석 수치 원소 수정에 성공했습니다.");
    }

    @PostMapping("/contact")
    public SuccessResult<ResourceNameListDto> businessContactAdd(@Validated @RequestBody SaveAndUpdateResourceNameDto saveResourceNameDto) {
        ResourceNameListDto businessContactNameListDto = resourceService.saveBusinessContact(saveResourceNameDto.getResourceName());
        return new SuccessResult<ResourceNameListDto>(CONTACT_ADD_SUCCESS, "거래처 추가에 성공했습니다.", businessContactNameListDto);
    }

    @PutMapping("/contact/{id}")
    public SuccessNoResult businessContactUpdate(@PathVariable("id") Long resourceNameId, @Validated @RequestBody SaveAndUpdateResourceNameDto updateResourceNameDto) {
        resourceService.updateResourceName(resourceNameId, updateResourceNameDto.getResourceName());

        return new SuccessNoResult(CONTACT_ADD_SUCCESS, "거래처 수정에 성공했습니다.");
    }

    @GetMapping
    public SuccessResult<TotalResourceNameDto> totalResourcesSearch() {
        TotalResourceNameDto totalResources = resourceService.findAllResources();
        return new SuccessResult<TotalResourceNameDto>(RESOURCE_TOTAL_SEARCH_SUCCESS, "ok", totalResources);

    }

    @DeleteMapping("/{id}")
    public SuccessNoResult resourceDelete(@PathVariable("id") Long resourceNameId) {
        resourceService.removeResourceName(resourceNameId);

        return new SuccessNoResult(RESOURCE_REMOVE_SUCCESS, "삭제 성공했습니다.");
    }





}
