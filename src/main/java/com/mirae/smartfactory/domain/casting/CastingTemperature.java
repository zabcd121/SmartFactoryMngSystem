package com.mirae.smartfactory.domain.casting;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class CastingTemperature {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long castingTemperatureId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "castingId")
    private Casting casting;

    private long furnaceTemperatureInit;
    private long furnaceTemperature15M;
    private long furnaceTemperature30M;
    private long furnaceTemperature45M;
    private long furnaceTemperature60M;
    private long furnaceTemperature75M;
    private long furnaceTemperature90M;
    private long coolingWaterTemperatureInit;
    private long coolingWaterTemperatureMiddle;
    private long coolingWaterTemperatureTerminated;

}
