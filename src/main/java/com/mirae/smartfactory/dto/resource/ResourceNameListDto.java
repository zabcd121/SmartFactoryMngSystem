package com.mirae.smartfactory.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNameListDto {
    private List<Map<String, Object>> nameList = new ArrayList<>();

    public void addResource(Long resourceNameId, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", resourceNameId);
        map.put("name", name);
        nameList.add(map);
    }
}
