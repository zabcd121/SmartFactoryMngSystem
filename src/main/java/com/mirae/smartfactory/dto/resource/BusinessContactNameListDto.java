package com.mirae.smartfactory.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessContactNameListDto {
    private List<String> nameList =  new ArrayList<>();

    public void addBusinessContact(String businessContact){
        nameList.add(businessContact);
    }

}
