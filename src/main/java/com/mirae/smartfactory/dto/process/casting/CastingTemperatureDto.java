package com.mirae.smartfactory.dto.process.casting;

import com.mirae.smartfactory.domain.model.process.casting.CastingTemperature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CastingTemperatureDto {

    private Long castingTemperatureId;
    private Integer furnaceTemperatureInit;
    private Integer furnaceTemperature15M;
    private Integer furnaceTemperature30M;
    private Integer furnaceTemperature45M;
    private Integer furnaceTemperature60M;
    private Integer furnaceTemperature75M;
    private Integer furnaceTemperature90M;
    private Integer coolingWaterTemperatureInit;
    private Integer coolingWaterTemperatureMiddle;
    private Integer coolingWaterTemperatureTerminated;

    public CastingTemperatureDto(CastingTemperature castingTemperature) {
        this.castingTemperatureId = castingTemperature.getCastingTemperatureId();
        this.furnaceTemperatureInit = castingTemperature.getFurnaceTemperatureInit();
        this.furnaceTemperature15M = castingTemperature.getFurnaceTemperature15M();
        this.furnaceTemperature30M = castingTemperature.getFurnaceTemperature30M();
        this.furnaceTemperature45M = castingTemperature.getFurnaceTemperature45M();
        this.furnaceTemperature60M = castingTemperature.getFurnaceTemperature60M();
        this.furnaceTemperature75M = castingTemperature.getFurnaceTemperature75M();
        this.furnaceTemperature90M = castingTemperature.getFurnaceTemperature90M();
        this.coolingWaterTemperatureInit = castingTemperature.getCoolingWaterTemperatureInit();
        this.coolingWaterTemperatureMiddle = castingTemperature.getCoolingWaterTemperatureMiddle();
        this.coolingWaterTemperatureTerminated = castingTemperature.getCoolingWaterTemperatureTerminated();
    }
}
