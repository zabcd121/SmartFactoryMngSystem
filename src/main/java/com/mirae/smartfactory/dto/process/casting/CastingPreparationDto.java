package com.mirae.smartfactory.dto.process.casting;

import com.mirae.smartfactory.domain.process.casting.CastingPreparation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CastingPreparationDto {

    private Integer rotorSpeed1;
    private Integer rotorSpeed2;
    private Integer arAmount1;
    private Integer arAmount2;
    private Integer operatingDuration;
    private Integer outGassingTemperature;
    private Integer atbSpeed;
    private String castingPreparationMore;

    public CastingPreparationDto(CastingPreparation castingPreparation) {
        this.rotorSpeed1 = castingPreparation.getRotorSpeed1();
        this.rotorSpeed2 = castingPreparation.getRotorSpeed2();
        this.arAmount1 = castingPreparation.getArAmount1();
        this.arAmount2 = castingPreparation.getArAmount2();
        this.operatingDuration = castingPreparation.getOperatingDuration();
        this.outGassingTemperature = castingPreparation.getOutGassingTemperature();
        this.atbSpeed = castingPreparation.getAtbSpeed();
        this.castingPreparationMore = castingPreparation.getCastingPreparationMore();
    }
}
