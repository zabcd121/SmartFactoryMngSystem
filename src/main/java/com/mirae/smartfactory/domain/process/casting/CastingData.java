package com.mirae.smartfactory.domain.process.casting;

import com.mirae.smartfactory.dto.process.casting.CastingDataDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CastingData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    private CastingData(Integer castingSpeedInit, Integer castingSpeedLampInit, Integer castingSpeedNormal, Integer castingSpeedLampStop, Integer castingSpeedTerminated, Integer castingTemperature0, Integer castingTemperature500, Integer castingTemperature1000, Integer castingTemperature2000, Integer castingTemperature3000, Integer castingTemperature4000, Integer castingTemperature5000, Integer castingTemperature5500, Integer castingTemperature6000, Integer castingTemperature6500, Integer castingTemperature7000, Integer coolingWaterInit, Integer coolingWaterLampInit, Integer coolingWaterNormal, Integer coolingWaterLampStop, Integer coolingWaterTerminated, Integer castingOilPressure, Integer castingOilCycle, Integer castingOilOnTime, Integer castingOilOffTime, Integer gasPressureNeutrality, Integer gasPressureInit, Integer gasPressureNormal, Integer sustainTime) {
        this.castingSpeedInit = castingSpeedInit;
        this.castingSpeedLampInit = castingSpeedLampInit;
        this.castingSpeedNormal = castingSpeedNormal;
        this.castingSpeedLampStop = castingSpeedLampStop;
        this.castingSpeedTerminated = castingSpeedTerminated;
        this.castingTemperature0 = castingTemperature0;
        this.castingTemperature500 = castingTemperature500;
        this.castingTemperature1000 = castingTemperature1000;
        this.castingTemperature2000 = castingTemperature2000;
        this.castingTemperature3000 = castingTemperature3000;
        this.castingTemperature4000 = castingTemperature4000;
        this.castingTemperature5000 = castingTemperature5000;
        this.castingTemperature5500 = castingTemperature5500;
        this.castingTemperature6000 = castingTemperature6000;
        this.castingTemperature6500 = castingTemperature6500;
        this.castingTemperature7000 = castingTemperature7000;
        this.coolingWaterInit = coolingWaterInit;
        this.coolingWaterLampInit = coolingWaterLampInit;
        this.coolingWaterNormal = coolingWaterNormal;
        this.coolingWaterLampStop = coolingWaterLampStop;
        this.coolingWaterTerminated = coolingWaterTerminated;
        this.castingOilPressure = castingOilPressure;
        this.castingOilCycle = castingOilCycle;
        this.castingOilOnTime = castingOilOnTime;
        this.castingOilOffTime = castingOilOffTime;
        this.gasPressureNeutrality = gasPressureNeutrality;
        this.gasPressureInit = gasPressureInit;
        this.gasPressureNormal = gasPressureNormal;
        this.sustainTime = sustainTime;
    }

    public static CastingData createCastingDataWithDto(CastingDataDto cdd) {
        return CastingData.createCastingData(cdd.getCastingSpeedInit(), cdd.getCastingSpeedLampInit(), cdd.getCastingSpeedNormal(), cdd.getCastingSpeedLampStop(),
                cdd.getCastingSpeedTerminated(), cdd.getCastingTemperature0(), cdd.getCastingTemperature500(), cdd.getCastingTemperature1000(), cdd.getCastingTemperature2000(),
                cdd.getCastingTemperature3000(), cdd.getCastingTemperature4000(), cdd.getCastingTemperature5000(), cdd.getCastingTemperature5500(), cdd.getCastingTemperature6000(), cdd.getCastingTemperature6500(), cdd.getCastingTemperature7000(),
                cdd.getCoolingWaterInit(), cdd.getCoolingWaterLampInit(), cdd.getCoolingWaterNormal(), cdd.getCoolingWaterLampStop(), cdd.getCoolingWaterTerminated(), cdd.getCastingOilPressure(),
                cdd.getCastingOilCycle(), cdd.getCastingOilOnTime(), cdd.getCastingOilOffTime(), cdd.getCastingOilPressure(), cdd.getGasPressureInit(), cdd.getGasPressureNormal(), cdd.getSustainTime());
    }

    public static CastingData createCastingData(Integer castingSpeedInit, Integer castingSpeedLampInit, Integer castingSpeedNormal, Integer castingSpeedLampStop, Integer castingSpeedTerminated, Integer castingTemperature0, Integer castingTemperature500, Integer castingTemperature1000, Integer castingTemperature2000, Integer castingTemperature3000, Integer castingTemperature4000, Integer castingTemperature5000, Integer castingTemperature5500, Integer castingTemperature6000, Integer castingTemperature6500, Integer castingTemperature7000, Integer coolingWaterInit, Integer coolingWaterLampInit, Integer coolingWaterNormal, Integer coolingWaterLampStop, Integer coolingWaterTerminated, Integer castingOilPressure, Integer castingOilCycle, Integer castingOilOnTime, Integer castingOilOffTime, Integer gasPressureNeutrality, Integer gasPressureInit, Integer gasPressureNormal, Integer sustainTime) {
        return new CastingData(castingSpeedInit, castingSpeedLampInit, castingSpeedNormal, castingSpeedLampStop, castingSpeedTerminated,
                castingTemperature0, castingTemperature500, castingTemperature1000, castingTemperature2000, castingTemperature3000, castingTemperature4000, castingTemperature5000, castingTemperature5500, castingTemperature6000, castingTemperature6500, castingTemperature7000,
                coolingWaterInit, coolingWaterLampInit, coolingWaterNormal, coolingWaterLampStop, coolingWaterTerminated, castingOilPressure, castingOilCycle, castingOilOnTime, castingOilOffTime, gasPressureNeutrality, gasPressureInit, gasPressureNormal, sustainTime);
    }
}
