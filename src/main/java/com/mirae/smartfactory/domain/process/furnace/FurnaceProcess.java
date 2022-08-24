package com.mirae.smartfactory.domain.process.furnace;

import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.domain.resource.Additive;
import com.mirae.smartfactory.domain.resource.Material;
import com.mirae.smartfactory.dto.MaterialDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.IngredientDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime chargingTime;
    private LocalDateTime outGassingTime;
    private LocalDateTime beforeTappingTime;
    private Integer outGassingInput;
    private Integer inclusionsInput;
    private Integer dustAmount;
    private Integer dustOutGassingBefore;
    private Integer dustOutGassingAfter;
    private String shipTo;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "processId")
    private Process process;

//    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "ingredientAnalysisId")
//    private IngredientAnalysis ingredientAnalysis;

    @OneToMany(mappedBy = "furnaceProcess", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    private FurnaceProcess(LocalDateTime chargingTime, LocalDateTime outGassingTime, LocalDateTime beforeTappingTime, Integer outGassingInput, Integer inclusionsInput,
                           Integer dustAmount, Integer dustOutGassingBefore, Integer dustOutGassingAfter, String shipTo, Process process) {
        this.chargingTime = chargingTime;
        this.outGassingTime = outGassingTime;
        this.beforeTappingTime = beforeTappingTime;
        this.outGassingInput = outGassingInput;
        this.inclusionsInput = inclusionsInput;
        this.dustAmount = dustAmount;
        this.dustOutGassingBefore = dustOutGassingBefore;
        this.dustOutGassingAfter = dustOutGassingAfter;
        this.shipTo = shipTo;
        this.process = process;
    }

//    public void setProcess(Process process) {
//        this.process = process;
//        process.setFurnaceProcess(this);
//    }

    private void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setFurnaceProcess(this);
    }

    public static FurnaceProcess createFurnaceProcessWithDto(FurnaceProcessDto fpd, Process process) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDto ingredientDto : fpd.getIngredients()) {
            ingredients.add(Ingredient.createIngredientWithDto(ingredientDto));
        }

        return FurnaceProcess.createFurnaceProcess(fpd.getChargingTime(), fpd.getOutGassingTime(), fpd.getBeforeTappingTime(),
                fpd.getOutGassingInput(), fpd.getInclusionsInput(), fpd.getDustAmount(), fpd.getDustOutGassingBefore(), fpd.getDustOutGassingAfter(),
                fpd.getShipTo(), process, ingredients);
    }

    public static FurnaceProcess createFurnaceProcess(LocalDateTime chargingTime, LocalDateTime outGassingTime, LocalDateTime beforeTappingTime, Integer outGassingInput, Integer inclusionsInput,
                                                      Integer dustAmount, Integer dustOutGassingBefore, Integer dustOutGassingAfter, String shipTo, Process process, List<Ingredient> ingredients) {

        FurnaceProcess furnaceProcess = new FurnaceProcess(chargingTime, outGassingTime, beforeTappingTime, outGassingInput,
                inclusionsInput, dustAmount, dustOutGassingBefore, dustOutGassingAfter, shipTo, process);

        for (Ingredient ingredient : ingredients) {
            furnaceProcess.addIngredient(ingredient);
        }

        return furnaceProcess;
    }

}
