package com.mirae.smartfactory.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OuterScrapNameListDto {

    private List<String> nameList = new ArrayList<>();

    public void addOuterScrap(String outerScrap) {
        nameList.add(outerScrap);
    }
}
