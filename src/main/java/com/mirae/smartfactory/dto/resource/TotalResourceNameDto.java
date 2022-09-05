package com.mirae.smartfactory.dto.resource;

import com.mirae.smartfactory.dto.resource.BusinessContactNameListDto;
import com.mirae.smartfactory.dto.resource.IngredientNameListDto;
import com.mirae.smartfactory.dto.resource.OuterScrapNameListDto;
import com.mirae.smartfactory.dto.resource.SiNameListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceNameDto {

    private OuterScrapNameListDto outerScrapNameList;
    private SiNameListDto siNameList;
    private IngredientNameListDto ingredientNameList;
    private BusinessContactNameListDto businessContactNameList;

}
