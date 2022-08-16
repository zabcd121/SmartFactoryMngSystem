package com.mirae.smartfactory.domain.casting;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class CastingData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long castingDataId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "castingId")
    private Casting casting;

    private int castingSpeedInit;
    private int castingSpeedLampInit;
    private int castingSpeedNormal;
    private int castingSpeedLampStop;
    private int castingSpeedTerminated;
    private int castingTemperature0;
    private int castingTemperature500;
    private int castingTemperature1000;
    private int castingTemperature2000;
    private int castingTemperature3000;
    private int castingTemperature4000;
    private int castingTemperature5000;
    private int castingTemperature5500;
    private int castingTemperature6000;
    private int castingTemperature6500;
    private int castingTemperature7000;

    private int coolingWaterInit;
    private int coolingWaterLampInit;
    private int coolingWaterNormal;
    private int coolingWaterLampStop;
    private int coolingWaterTerminated;

    private int castingOilPressure;
    private int castingOilCycle;
    private int castingOilOnTime;
    private int castingOilOffTime;

    private int gasPressureNeutrality;
    private int gasPressureInit;
    private int gasPressureNormal;

    private int sustainTime;

}
