package com.mirae.smartfactory.dto.resource;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.domain.model.resource.Additive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdditiveDto {

    private Long additiveId;
    private Integer sequence;
    private String additiveName;
    private Integer additiveWeight;
    public static AdditiveDto of(Additive additive) {
        return new AdditiveDto(additive.getAdditiveId(), additive.getSequence(), additive.getAdditiveName(), additive.getAdditiveWeight());
    }

    public static Additive toEntity(AdditiveDto additiveDto) {
        return Additive.createAdditive(additiveDto.getSequence(), additiveDto.getAdditiveName(), additiveDto.getAdditiveWeight());
    }

    public static Additive toEntity(AdditiveDto additiveDto, Process process) {
        return Additive.createAdditive(additiveDto.getSequence(), additiveDto.getAdditiveName(), additiveDto.getAdditiveWeight(), process);
    }

}
