package com.mirae.smartfactory.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SiNameListDto {
    private List<String> siNameList = new ArrayList<>();

    public void addSi(String si) {
        siNameList.add(si);
    }
}
