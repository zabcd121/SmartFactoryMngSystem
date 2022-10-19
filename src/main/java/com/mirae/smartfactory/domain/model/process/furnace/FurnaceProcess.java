package com.mirae.smartfactory.domain.model.process.furnace;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.domain.service.FurnaceProcessUpdateService;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessSaveDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.IngredientSaveDto;
import com.mirae.smartfactory.dto.process.furnace.IngredientDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FurnaceProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(fetch = EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "processId")
    private Process process;

    @OneToMany(mappedBy = "furnaceProcess", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    private FurnaceProcess(LocalTime chargingTime, LocalTime outGassingTime, LocalTime beforeTappingTime, Integer outGassingInput, Integer inclusionsInput,
                           Integer ashesAmount, Integer ashesOutGassingBefore, Integer ashesOutGassingAfter, String shipTo, Process process) {
        this.chargingTime = chargingTime;
        this.outGassingTime = outGassingTime;
        this.beforeTappingTime = beforeTappingTime;
        this.outGassingInput = outGassingInput;
        this.inclusionsInput = inclusionsInput;
        this.ashesAmount = ashesAmount;
        this.ashesOutGassingBefore = ashesOutGassingBefore;
        this.ashesOutGassingAfter = ashesOutGassingAfter;
        this.shipTo = shipTo;
        this.process = process;
    }

    private FurnaceProcess(Long furnaceProcessId, LocalTime chargingTime, LocalTime outGassingTime, LocalTime beforeTappingTime, Integer outGassingInput, Integer inclusionsInput,
                           Integer ashesAmount, Integer ashesOutGassingBefore, Integer ashesOutGassingAfter, String shipTo, Process process) {
        this.furnaceProcessId = furnaceProcessId;
        this.chargingTime = chargingTime;
        this.outGassingTime = outGassingTime;
        this.beforeTappingTime = beforeTappingTime;
        this.outGassingInput = outGassingInput;
        this.inclusionsInput = inclusionsInput;
        this.ashesAmount = ashesAmount;
        this.ashesOutGassingBefore = ashesOutGassingBefore;
        this.ashesOutGassingAfter = ashesOutGassingAfter;
        this.shipTo = shipTo;
        this.process = process;
    }

    private void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        if(ingredient.getFurnaceProcess() != this) {
            ingredient.setFurnaceProcess(this);
        }
    }

    public void changeState(FurnaceProcessDto fpDto, FurnaceProcessUpdateService fpUpdateService) {
        // 저장되어있던 원료 중 수정과정에서 삭제된 것을 추척하여 컬렉션에서 삭제하고 Ingredient 쓰레기 데이터도 db에서 삭제함.
        List<Ingredient> removedIngredients = fpUpdateService.traceRemovedIngredients(ingredients, fpDto.getIngredients());
        for (Ingredient removedIngredient : removedIngredients) {
            this.ingredients.remove(removedIngredient);
        }

        fpUpdateService.deleteRemovedIngredients(removedIngredients);

        this.chargingTime = fpDto.getChargingTime();
        this.outGassingTime = fpDto.getOutGassingTime();
        this.beforeTappingTime = fpDto.getBeforeTappingTime();
        this.outGassingInput = fpDto.getOutGassingInput();
        this.inclusionsInput = fpDto.getInclusionsInput();
        this.ashesAmount = fpDto.getAshesAmount();
        this.ashesOutGassingBefore = fpDto.getAshesOutGassingBefore();
        this.ashesOutGassingAfter = fpDto.getAshesOutGassingAfter();
        this.shipTo = fpDto.getShipTo();
        this.process.changeState(fpDto.getProcess(), fpUpdateService);

        for (IngredientDto ingredientDto : fpDto.getIngredients()) {
            for (Ingredient ingredient : this.ingredients) {
                if(ingredient.getIngredientAnalysisId() == ingredientDto.getIngredientAnalysisId()) {
                    ingredient.changeState(ingredientDto);
                    break;
                }
            }
        }

        for(IngredientDto ingredientDto : fpDto.getIngredients()) {
            if(ingredientDto.getIngredientAnalysisId() == null) {
                addIngredient(IngredientDto.toEntity(ingredientDto));
//                this.ingredients.add(IngredientDto.toEntity(ingredientDto, this));
            }
        }
    }

    public static FurnaceProcess createFurnaceProcessWithDtoAndId(Long furnaceProcessId, FurnaceProcessSaveDto fpd, Process process) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientSaveDto ingredientDto : fpd.getIngredients()) {
            ingredients.add(IngredientSaveDto.toEntity(ingredientDto));
        }

        return FurnaceProcess.createFurnaceProcessWithId(furnaceProcessId, fpd.getChargingTime(), fpd.getOutGassingTime(), fpd.getBeforeTappingTime(),
                fpd.getOutGassingInput(), fpd.getInclusionsInput(), fpd.getAshesAmount(), fpd.getAshesOutGassingBefore(), fpd.getAshesOutGassingAfter(),
                fpd.getShipTo(), process, ingredients);
    }

    public static FurnaceProcess createFurnaceProcessWithDto(FurnaceProcessSaveDto fpd, Process process) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientSaveDto ingredientDto : fpd.getIngredients()) {
            ingredients.add(IngredientSaveDto.toEntity(ingredientDto));
        }

        return FurnaceProcess.createFurnaceProcess(fpd.getChargingTime(), fpd.getOutGassingTime(), fpd.getBeforeTappingTime(),
                fpd.getOutGassingInput(), fpd.getInclusionsInput(), fpd.getAshesAmount(), fpd.getAshesOutGassingBefore(), fpd.getAshesOutGassingAfter(),
                fpd.getShipTo(), process, ingredients);
    }

    public static FurnaceProcess createFurnaceProcess(LocalTime chargingTime, LocalTime outGassingTime, LocalTime beforeTappingTime, Integer outGassingInput, Integer inclusionsInput,
                                                      Integer ashesAmount, Integer ashesOutGassingBefore, Integer ashesOutGassingAfter, String shipTo, Process process, List<Ingredient> ingredients) {

        FurnaceProcess furnaceProcess = new FurnaceProcess(chargingTime, outGassingTime, beforeTappingTime, outGassingInput,
                inclusionsInput, ashesAmount, ashesOutGassingBefore, ashesOutGassingAfter, shipTo, process);

        for (Ingredient ingredient : ingredients) {
            furnaceProcess.addIngredient(ingredient);
        }

        return furnaceProcess;
    }

    public static FurnaceProcess createFurnaceProcess(LocalTime chargingTime, LocalTime outGassingTime, LocalTime beforeTappingTime, Integer outGassingInput, Integer inclusionsInput,
                                                      Integer ashesAmount, Integer ashesOutGassingBefore, Integer ashesOutGassingAfter, String shipTo, Process process) {

        FurnaceProcess furnaceProcess = new FurnaceProcess(chargingTime, outGassingTime, beforeTappingTime, outGassingInput,
                inclusionsInput, ashesAmount, ashesOutGassingBefore, ashesOutGassingAfter, shipTo, process);


        return furnaceProcess;
    }

    public static FurnaceProcess createFurnaceProcessWithId(Long furnaceProcessId, LocalTime chargingTime, LocalTime outGassingTime, LocalTime beforeTappingTime, Integer outGassingInput, Integer inclusionsInput,
                                                      Integer ashesAmount, Integer ashesOutGassingBefore, Integer ashesOutGassingAfter, String shipTo, Process process, List<Ingredient> ingredients) {

        FurnaceProcess furnaceProcess = new FurnaceProcess(furnaceProcessId, chargingTime, outGassingTime, beforeTappingTime, outGassingInput,
                inclusionsInput, ashesAmount, ashesOutGassingBefore, ashesOutGassingAfter, shipTo, process);

        for (Ingredient ingredient : ingredients) {
            furnaceProcess.addIngredient(ingredient);
        }

        return furnaceProcess;
    }


}
