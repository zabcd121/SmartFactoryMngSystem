package com.mirae.smartfactory.domain.process.furnace;

import com.mirae.smartfactory.dto.process.furnace.IngredientAnalysisDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientAnalysis {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ingredientAnalysisId;

//    private int sequenceNumber;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "furnaceProcessId")
    private FurnaceProcess furnaceProcess;

    private String materialName;

    private Float weight;

    public IngredientAnalysis(String materialName, Float weight) {
        this.materialName = materialName;
        this.weight = weight;
    }

    public static IngredientAnalysis createIngredientAnalysisWithDto(IngredientAnalysisDto ingredientAnalysisDto) {
        return IngredientAnalysis.createIngredientAnalysis(ingredientAnalysisDto.getAi(), ingredientAnalysisDto.getSi(),
                ingredientAnalysisDto.getFe(), ingredientAnalysisDto.getCu(), ingredientAnalysisDto.getMg(),
                ingredientAnalysisDto.getZn(), ingredientAnalysisDto.getMn());
    }

    public static IngredientAnalysis createIngredientAnalysis(String materialName, Float weight) {
        return new IngredientAnalysis(materialName, weight);
    }
}
