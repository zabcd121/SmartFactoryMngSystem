package com.mirae.smartfactory.dto.process.furnace;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.domain.model.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.dto.process.ProcessDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FurnaceProcessDto {

    private Long furnaceProcessId;
    private LocalTime chargingTime;
    private LocalTime outGassingTime;
    private LocalTime beforeTappingTime;
    private Integer outGassingInput;
    private Integer inclusionsInput;
    private Integer ashesAmount;
    private Integer ashesOutGassingBefore;
    private Integer ashesOutGassingAfter;
    private String shipTo;

    private ProcessDto process;

    private List<IngredientDto> ingredients;

    public FurnaceProcessDto(FurnaceProcess furnaceProcess) {
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
        process = ProcessDto.of(furnaceProcess.getProcess());
        this.ingredients = furnaceProcess.getIngredients().stream()
                .map(ingredient -> IngredientDto.of(ingredient))
                .collect(Collectors.toList());
    }

    public static FurnaceProcessDto of(FurnaceProcess fp) {
        return new FurnaceProcessDto(fp);
    }

    public static FurnaceProcess toEntity(FurnaceProcessDto fpDto, Process process) {
        return FurnaceProcess.createFurnaceProcess(fpDto.getChargingTime(), fpDto.getOutGassingTime(), fpDto.getBeforeTappingTime(),
                fpDto.getOutGassingInput(), fpDto.getInclusionsInput(), fpDto.getAshesAmount(), fpDto.getAshesOutGassingBefore(), fpDto.getAshesOutGassingAfter(),
                fpDto.getShipTo(), process, fpDto.getIngredients().stream().map(ingredientDto -> IngredientDto.toEntity(ingredientDto)).collect(Collectors.toList()));
    }
}
