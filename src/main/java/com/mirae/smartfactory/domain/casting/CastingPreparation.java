package com.mirae.smartfactory.domain.casting;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class CastingPreparation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long castingPreparationId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "castingId")
    private Casting casting;

    private long rotorSpeed1;
    private long rotorSpeed2;
    private long arAmount1;
    private long arAmount2;
    private int operatingDuration;
    private long outGassingTemperature;
    private long ATBSpeed;
    private String castingPreparationMore;
}
