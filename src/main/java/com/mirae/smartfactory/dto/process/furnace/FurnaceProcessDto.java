package com.mirae.smartfactory.dto.process.furnace;

import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.dto.MaterialDto;
import com.mirae.smartfactory.dto.process.ProcessDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FurnaceProcessDto {

//    private Long furnaceProcessId;

    private LocalDateTime chargingTime;
    private LocalDateTime outGassingTime;
    private LocalDateTime beforeTappingTime;
    private Integer outGassingInput;
    private Integer inclusionsInput;
    private Integer dustAmount;
    private Integer dustOutGassingBefore;
    private Integer dustOutGassingAfter;
    private String shipTo;

    private ProcessDto process;

    private List<IngredientDto> ingredients;

    public FurnaceProcessDto(FurnaceProcess furnaceProcess) {
//        furnaceProcessId = furnaceProcess.getFurnaceProcessId();
        chargingTime = furnaceProcess.getChargingTime();
        outGassingTime = furnaceProcess.getOutGassingTime();
        beforeTappingTime = furnaceProcess.getBeforeTappingTime();
        outGassingInput = furnaceProcess.getOutGassingInput();
        inclusionsInput = furnaceProcess.getInclusionsInput();
        dustAmount = furnaceProcess.getDustAmount();
        dustOutGassingBefore = furnaceProcess.getDustOutGassingBefore();
        dustOutGassingAfter = furnaceProcess.getDustOutGassingAfter();
        shipTo = furnaceProcess.getShipTo();
        process = new ProcessDto(furnaceProcess.getProcess());
        this.ingredients = furnaceProcess.getIngredients().stream()
                .map(ingredient -> new IngredientDto(ingredient))
                .collect(Collectors.toList());
    }

//    public FurnaceProcessDto(Long furnaceProcessId, LocalDateTime chargingTime, LocalDateTime outGassingTime, LocalDateTime beforeTappingTime, Integer outGassingInput, Integer inclusionsInput, Integer dustAmount, Integer dustOutGassingBefore, Integer dustOutGassingAfter, String shipTo, IngredientAnalysisDto ingredientAnalysis) {
//        this.furnaceProcessId = furnaceProcessId;
//        this.chargingTime = chargingTime;
//        this.outGassingTime = outGassingTime;
//        this.beforeTappingTime = beforeTappingTime;
//        this.outGassingInput = outGassingInput;
//        this.inclusionsInput = inclusionsInput;
//        this.dustAmount = dustAmount;
//        this.dustOutGassingBefore = dustOutGassingBefore;
//        this.dustOutGassingAfter = dustOutGassingAfter;
//        this.shipTo = shipTo;
//        this.ingredientAnalysis = ingredientAnalysis;
//    }
}
