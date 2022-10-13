package com.mirae.smartfactory.dto.process.casting;

import com.mirae.smartfactory.domain.model.process.casting.CastingData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CastingDataDto {
    private Long castingDataId;
    private Integer castingSpeedInit;
    private Integer castingSpeedLampInit;
    private Integer castingSpeedNormal;
    private Integer castingSpeedLampStop;
    private Integer castingSpeedTerminated;
    private Integer castingTemperature0;
    private Integer castingTemperature500;
    private Integer castingTemperature1000;
    private Integer castingTemperature2000;
    private Integer castingTemperature3000;
    private Integer castingTemperature4000;
    private Integer castingTemperature5000;
    private Integer castingTemperature5500;
    private Integer castingTemperature6000;
    private Integer castingTemperature6500;
    private Integer castingTemperature7000;

    private Integer coolingWaterInit;
    private Integer coolingWaterLampInit;
    private Integer coolingWaterNormal;
    private Integer coolingWaterLampStop;
    private Integer coolingWaterTerminated;

    private Integer castingOilPressure;
    private Integer castingOilCycle;
    private Integer castingOilOnTime;
    private Integer castingOilOffTime;

    private Integer gasPressureNeutrality;
    private Integer gasPressureInit;
    private Integer gasPressureNormal;

    private Integer sustainTime;

    public CastingDataDto(CastingData castingData) {
        this.castingDataId = castingData.getCastingDataId();
        this.castingSpeedInit = castingData.getCastingSpeedInit();
        this.castingSpeedLampInit = castingData.getCastingSpeedLampInit();
        this.castingSpeedNormal = castingData.getCastingSpeedNormal();
        this.castingSpeedLampStop = castingData.getCastingSpeedLampStop();
        this.castingSpeedTerminated = castingData.getCastingSpeedTerminated();
        this.castingTemperature0 = castingData.getCastingTemperature0();
        this.castingTemperature500 = castingData.getCastingTemperature500();
        this.castingTemperature1000 = castingData.getCastingTemperature1000();
        this.castingTemperature2000 = castingData.getCastingTemperature2000();
        this.castingTemperature3000 = castingData.getCastingTemperature3000();
        this.castingTemperature4000 = castingData.getCastingTemperature4000();
        this.castingTemperature5000 = castingData.getCastingTemperature5000();
        this.castingTemperature5500 = castingData.getCastingTemperature5500();
        this.castingTemperature6000 = castingData.getCastingTemperature6000();
        this.castingTemperature6500 = castingData.getCastingTemperature6500();
        this.castingTemperature7000 = castingData.getCastingTemperature7000();
        this.coolingWaterInit = castingData.getCoolingWaterInit();
        this.coolingWaterLampInit = castingData.getCoolingWaterLampInit();
        this.coolingWaterNormal = castingData.getCoolingWaterNormal();
        this.coolingWaterLampStop = castingData.getCoolingWaterLampStop();
        this.coolingWaterTerminated = castingData.getCoolingWaterTerminated();
        this.castingOilPressure = castingData.getCastingOilPressure();
        this.castingOilCycle = castingData.getCastingOilCycle();
        this.castingOilOnTime = castingData.getCastingOilOnTime();
        this.castingOilOffTime = castingData.getCastingOilOffTime();
        this.gasPressureNeutrality = castingData.getGasPressureNeutrality();
        this.gasPressureInit = castingData.getGasPressureInit();
        this.gasPressureNormal = castingData.getGasPressureNormal();
        this.sustainTime = castingData.getSustainTime();
    }
}
