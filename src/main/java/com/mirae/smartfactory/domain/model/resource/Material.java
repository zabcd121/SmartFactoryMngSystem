package com.mirae.smartfactory.domain.model.resource;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.dto.resource.MaterialDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Material {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long materialId;

    @Enumerated(value = EnumType.STRING)
    private ResourceType resourceType;

    private Integer sequence;

    private String materialName;

    private Integer weight;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processId")
    private Process process;


    public Material(ResourceType materialType, Integer sequence, String materialName, Integer weight, Process process) {
        this.resourceType = materialType;
        this.sequence = sequence;
        this.materialName = materialName;
        this.weight = weight;
        this.process = process;
    }

    public Material(ResourceType materialType, Integer sequence, String materialName, Integer weight) {
        this.resourceType = materialType;
        this.sequence = sequence;
        this.materialName = materialName;
        this.weight = weight;
    }

    public void changeState(MaterialDto materialDto) {
        this.resourceType = materialDto.getResourceType();
        this.sequence = materialDto.getSequence();
        this.materialName = materialDto.getMaterialName();
        this.weight = materialDto.getWeight();
    }

//    public static Material createMaterialWithDto(MaterialDto materialDto) {
//        Material material = Material.createMaterial(materialDto.getResourceType(), materialDto.getMaterialName(), materialDto.getWeight());
//
//        return material;
//    }

    public static Material createMaterial(ResourceType resourceType, Integer sequence, String materialName, Integer weight, Process process) {
        return new Material(resourceType, sequence, materialName, weight, process);
    }

    public static Material createMaterial(ResourceType resourceType, Integer sequence, String materialName, Integer weight) {
        return new Material(resourceType, sequence, materialName, weight);
    }
}
