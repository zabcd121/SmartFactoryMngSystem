package com.mirae.smartfactory.domain.model.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourceName {

    @Id @GeneratedValue
    private Long resourceNameId;

    @Enumerated(value = EnumType.STRING)
    private ResourceType resourceType;
    private String materialName;

//    INGOT("인코드"),
//    SELF("자체"),
//    BACK("뒤판"),
//    BAG("마대"),
//    JAE("재괴"),
//    BENDING("밴딩"),
//    CHIP("칩"),
//    COMPRESSION("압축"),
//    LOOSE("LOOSE");


    private ResourceName(ResourceType resourceType, String materialName) {
        this.resourceType = resourceType;
        this.materialName = materialName;
    }

    public static ResourceName createResourceName(ResourceType resourceType, String materialName) {
        return new ResourceName(resourceType, materialName);
    }

    public void changeMaterialName(String materialName){
        this.materialName = materialName;
    }

}
