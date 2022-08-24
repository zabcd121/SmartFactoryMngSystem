package com.mirae.smartfactory.dto.process.casting;

import com.mirae.smartfactory.domain.process.casting.Casting;
import com.mirae.smartfactory.dto.BilletDto;
import com.mirae.smartfactory.dto.process.ProcessDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CastingDto {
    private Integer dailyProcessId;
    private LocalDateTime tappingStartTime;
    private LocalDateTime tappingEndTime;
    private String operator;
    private String shifter;

    private String remarks;

    private ProcessSimpleDto process;

    private CastingPreparationDto castingPreparation;
    private CastingDataDto castingData;
    private CastingTemperatureDto castingTemperature;
    private BilletDto billet;

    public CastingDto(Casting casting, Integer dailyProcessId) {
        this.dailyProcessId = dailyProcessId;
        this.tappingStartTime = casting.getTappingStartTime();
        this.tappingEndTime = casting.getTappingEndTime();
        this.operator = casting.getOperator();
        this.shifter = casting.getShifter();
        this.remarks = casting.getRemarks();
        this.process = new ProcessSimpleDto(casting.getProcess());
        this.castingPreparation = new CastingPreparationDto(casting.getCastingPreparation());
        this.castingData = new CastingDataDto(casting.getCastingData());
        this.castingTemperature = new CastingTemperatureDto(casting.getCastingTemperature());
        this.billet = new BilletDto(casting.getBillet());
    }
}
