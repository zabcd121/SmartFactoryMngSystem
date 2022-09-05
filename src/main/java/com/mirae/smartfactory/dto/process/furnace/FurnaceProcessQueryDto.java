package com.mirae.smartfactory.dto.process.furnace;

import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.dto.process.ProcessDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FurnaceProcessQueryDto {

    private Long furnaceProcessId;
    private LocalDateTime chargingTime;
    private LocalDateTime outGassingTime;
    private LocalDateTime beforeTappingTime;
    private Integer outGassingInput;
    private Integer inclusionsInput;
    private Integer ashesAmount;
    private Integer ashesOutGassingBefore;
    private Integer ashesOutGassingAfter;
    private String shipTo;

    private ProcessDto process;

    private List<IngredientDto> ingredients;

    public FurnaceProcessQueryDto(FurnaceProcess furnaceProcess) {
        this.furnaceProcessId = furnaceProcess.getFurnaceProcessId();
        chargingTime = furnaceProcess.getChargingTime();
        outGassingTime = furnaceProcess.getOutGassingTime();
        beforeTappingTime = furnaceProcess.getBeforeTappingTime();
        outGassingInput = furnaceProcess.getOutGassingInput();
        inclusionsInput = furnaceProcess.getInclusionsInput();
        ashesAmount = furnaceProcess.getAshesAmount();
        ashesOutGassingBefore = furnaceProcess.getAshesOutGassingBefore();
        ashesOutGassingAfter = furnaceProcess.getAshesOutGassingAfter();
        shipTo = furnaceProcess.getShipTo();
        process = new ProcessDto(furnaceProcess.getProcess());
        this.ingredients = furnaceProcess.getIngredients().stream()
                .map(ingredient -> new IngredientDto(ingredient))
                .collect(Collectors.toList());
    }
}
