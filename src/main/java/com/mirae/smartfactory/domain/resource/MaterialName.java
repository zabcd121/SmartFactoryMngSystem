package com.mirae.smartfactory.domain.resource;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
public class MaterialName {

    @Id @GeneratedValue
    private long materialNameId;
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


}
