package com.mirae.smartfactory.domain.model.process.gbf;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class SensingGBF {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long sensingGBFId;
    private LocalDateTime measuredAt;
    private long GBFTemperature;
    private long hitter1Temperature;
    private long hitter2Temperature;
    private long GBFGasSupply;
    private long GBFProtectionGas;
    private long GBFGas1Flux;
    private long GBFGas2Flux;
}
