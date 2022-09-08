package com.mirae.smartfactory.dto.resource;

import com.mirae.smartfactory.domain.resource.Material;
import com.mirae.smartfactory.domain.resource.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(of = "processId")
@AllArgsConstructor
public class MaterialDto {

    private ResourceType resourceType;

    private String materialName;

    private Integer weight;

//    private Long processId;


    public MaterialDto(Material material) {
        this.resourceType = material.getResourceType();
        this.materialName = material.getMaterialName();
        this.weight = material.getWeight();
//        this.processId = material.getProcess().getProcessId();
    }

//    public MaterialDto(Long processId, MaterialType materialType, String materialName, Long weight) {
//        this.processId = processId;
//        this.materialType = materialType;
//        this.materialName = materialName;
//        this.weight = weight;
//    }
}
