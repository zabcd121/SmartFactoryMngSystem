package com.mirae.smartfactory.dto.resource;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.domain.model.resource.Material;
import com.mirae.smartfactory.domain.model.resource.ResourceType;
import com.mirae.smartfactory.dto.process.ProcessDto;
import lombok.*;

@Getter
@EqualsAndHashCode(of = "processId")
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {
    private Long materialId;

    private ResourceType resourceType;

    private Integer sequence;

    private String materialName;

    private Integer weight;

//    public MaterialDto(Material material) {
//        this.resourceType = material.getResourceType();
//        this.materialName = material.getMaterialName();
//        this.weight = material.getWeight();
////        this.processId = material.getProcess().getProcessId();
//    }

//    public MaterialDto(Long processId, MaterialType materialType, String materialName, Long weight) {
//        this.processId = processId;
//        this.materialType = materialType;
//        this.materialName = materialName;
//        this.weight = weight;
//    }

    public static MaterialDto of(Material material) {
        return new MaterialDto(material.getMaterialId(), material.getResourceType(), material.getSequence(), material.getMaterialName(), material.getWeight());
    }

    public static Material toEntity(MaterialDto materialDto) {
        return Material.createMaterial(materialDto.getResourceType(), materialDto.getSequence(), materialDto.getMaterialName(), materialDto.getWeight());
    }

    public static Material toEntity(MaterialDto materialDto, Process process) {
        return Material.createMaterial(materialDto.getResourceType(), materialDto.getSequence(), materialDto.getMaterialName(), materialDto.getWeight(), process);
    }
}
