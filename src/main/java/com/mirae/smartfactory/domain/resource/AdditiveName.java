package com.mirae.smartfactory.domain.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AdditiveName {
    STONE("원석"),
    ALLOY("모합금"),
    MG("MG");

    final private String krAdditiveName;
    AdditiveName(String krAdditiveName){
        this.krAdditiveName = krAdditiveName;
    }
    public String getName(){
        return krAdditiveName;
    }
    public static AdditiveName nameOf(String name) {
        for (AdditiveName additive : AdditiveName.values()) {
            if (additive.getName().equals(name)) {
                return additive;
            }
        }
        return null;
    }
    public static List<String> AllKrNames(){
        return Arrays.stream(AdditiveName.values()).map(a -> a.getName()).collect(Collectors.toCollection(ArrayList::new));
    }
}
