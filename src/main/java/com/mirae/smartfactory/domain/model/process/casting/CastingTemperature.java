package com.mirae.smartfactory.domain.model.process.casting;

import com.mirae.smartfactory.dto.process.casting.CastingTemperatureDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CastingTemperature {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    public CastingTemperature(Integer furnaceTemperatureInit, Integer furnaceTemperature15M, Integer furnaceTemperature30M, Integer furnaceTemperature45M, Integer furnaceTemperature60M, Integer furnaceTemperature75M, Integer furnaceTemperature90M, Integer coolingWaterTemperatureInit, Integer coolingWaterTemperatureMiddle, Integer coolingWaterTemperatureTerminated) {
        this.furnaceTemperatureInit = furnaceTemperatureInit;
        this.furnaceTemperature15M = furnaceTemperature15M;
        this.furnaceTemperature30M = furnaceTemperature30M;
        this.furnaceTemperature45M = furnaceTemperature45M;
        this.furnaceTemperature60M = furnaceTemperature60M;
        this.furnaceTemperature75M = furnaceTemperature75M;
        this.furnaceTemperature90M = furnaceTemperature90M;
        this.coolingWaterTemperatureInit = coolingWaterTemperatureInit;
        this.coolingWaterTemperatureMiddle = coolingWaterTemperatureMiddle;
        this.coolingWaterTemperatureTerminated = coolingWaterTemperatureTerminated;
    }

    public static CastingTemperature createCastingTemperatureWithDto(CastingTemperatureDto ctd) {
        return CastingTemperature.createCastingTemperature(ctd.getFurnaceTemperatureInit(), ctd.getFurnaceTemperature15M(), ctd.getFurnaceTemperature30M(), ctd.getFurnaceTemperature45M(), ctd.getFurnaceTemperature60M(), ctd.getFurnaceTemperature75M(), ctd.getFurnaceTemperature90M(),
                ctd.getCoolingWaterTemperatureInit(), ctd.getCoolingWaterTemperatureMiddle(), ctd.getCoolingWaterTemperatureTerminated());
    }

    public static CastingTemperature createCastingTemperature(Integer furnaceTemperatureInit, Integer furnaceTemperature15M, Integer furnaceTemperature30M, Integer furnaceTemperature45M, Integer furnaceTemperature60M, Integer furnaceTemperature75M, Integer furnaceTemperature90M, Integer coolingWaterTemperatureInit, Integer coolingWaterTemperatureMiddle, Integer coolingWaterTemperatureTerminated) {
        return new CastingTemperature(furnaceTemperatureInit, furnaceTemperature15M, furnaceTemperature30M, furnaceTemperature45M, furnaceTemperature60M, furnaceTemperature75M, furnaceTemperature90M,
                coolingWaterTemperatureInit, coolingWaterTemperatureMiddle, coolingWaterTemperatureTerminated);
    }

    public void changeState(CastingTemperatureDto castingTemperature) {
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
