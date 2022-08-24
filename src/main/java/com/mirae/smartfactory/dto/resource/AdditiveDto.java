package com.mirae.smartfactory.dto.resource;

import com.mirae.smartfactory.domain.resource.Additive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdditiveDto {

//    private Long processId;
    private String additiveName;
    private Long additiveWeight;

    public AdditiveDto(Additive additive) {
//        this.processId = additive.getProcess().getProcessId();
        this.additiveName = additive.getAdditiveName();
        this.additiveWeight = additive.getAdditiveWeight();
    }

}
