package com.mirae.smartfactory.domain.casting;

import com.mirae.smartfactory.domain.furnace.Process;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Casting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long castingId;
    private LocalDateTime tappingStartTime;
    private LocalDateTime tappingEndTime;
    private String operator;
    private String shifter;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "processId")
    private Process process;

    @OneToOne(mappedBy = "casting", fetch = LAZY, cascade = CascadeType.ALL)
    CastingPreparation castingPreparation;

    @OneToOne(mappedBy = "casting", fetch = LAZY, cascade = CascadeType.ALL)
    CastingData castingData;

    @OneToOne(mappedBy = "casting", fetch = LAZY, cascade = CascadeType.ALL)
    CastingTemperature castingTemperature;
}
