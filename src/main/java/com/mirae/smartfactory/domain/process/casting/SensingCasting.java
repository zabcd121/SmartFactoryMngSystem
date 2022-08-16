package com.mirae.smartfactory.domain.casting;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class SensingCasting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long sensingCastingId;

    private LocalDateTime measuredAt;

    private long speed;
    private long length;
    private long dividerTemperature;
    private long hydraulicOilTemperature;
    private long coolingWaterTemperature;
    private long coolingWaterVolume;
    private long waterWork;
    private long waterLevel;
}
