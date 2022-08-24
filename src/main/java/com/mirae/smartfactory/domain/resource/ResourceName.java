package com.mirae.smartfactory.domain.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MaterialName {

    @Id @GeneratedValue
    private Long materialNameId;
    private MaterialType materialType;
    private String krMaterialName;

//    INGOT("인코드"),
//    SELF("자체"),
//    BACK("뒤판"),
//    BAG("마대"),
//    JAE("재괴"),
//    BENDING("밴딩"),
//    CHIP("칩"),
//    COMPRESSION("압축"),
//    LOOSE("LOOSE");


    private MaterialName(MaterialType materialType, String krMaterialName) {
        this.materialType = materialType;
        this.krMaterialName = krMaterialName;
    }

    public static MaterialName createMaterialName(MaterialType materialType, String krMaterialName) {
        return new MaterialName(materialType, krMaterialName);
    }

}
