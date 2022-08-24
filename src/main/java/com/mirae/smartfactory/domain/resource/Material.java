package com.mirae.smartfactory.domain.resource;

import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.dto.MaterialDto;
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
    private long materialId;

    @Enumerated(value = EnumType.STRING)
    private ResourceType resourceType;

    private String materialName;

    private long weight;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processId")
    private Process process;


    public Material(ResourceType materialType, String materialName, long weight) {
        this.resourceType = materialType;
        this.materialName = materialName;
        this.weight = weight;
    }

//    public void setProcess(Process process) {
//        this.process = process;
//        process.getMaterials().add(this);
//    }

    public static Material createMaterialWithDto(MaterialDto materialDto) {
        Material material = Material.createMaterial(materialDto.getResourceType(), materialDto.getMaterialName(), materialDto.getWeight());

        return material;
    }

    public static Material createMaterial(ResourceType resourceType, String materialName, long weight) {
        return new Material(resourceType, materialName, weight);
    }
}
