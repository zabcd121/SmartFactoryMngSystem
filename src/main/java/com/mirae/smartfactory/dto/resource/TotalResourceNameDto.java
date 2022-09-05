package com.mirae.smartfactory.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TotalResourceNameDto {

    private ResourceNameListDto outerScrapNameList;
    private ResourceNameListDto siNameList;
    private ResourceNameListDto ingredientNameList;
    private ResourceNameListDto businessContactNameList;

}
