package com.mirae.smartfactory.domain.model.process.furnace;

import com.mirae.smartfactory.domain.model.resource.ResourceType;
import com.mirae.smartfactory.dto.process.furnace.IngredientDto;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Ingredient {

    @Enumerated(value = EnumType.STRING)
    private final ResourceType resourceType = ResourceType.INGREDIENT;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ingredientAnalysisId;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "furnaceProcessId")
    private FurnaceProcess furnaceProcess;

    private Integer sequence;
    private String ingredientName;

    private Float weight;

    public Ingredient(Integer sequence, String ingredientName, Float weight) {
        this.sequence = sequence;
        this.ingredientName = ingredientName;
        this.weight = weight;
    }

    public void setFurnaceProcess(FurnaceProcess furnaceProcess) {
        if(this.furnaceProcess != null){
            this.furnaceProcess.getIngredients().remove(this);
        }
        this.furnaceProcess = furnaceProcess;
        furnaceProcess.getIngredients().add(this);
    }

    public void changeState(IngredientDto ingredientDto) {
        this.sequence = ingredientDto.getSequence();
        this.ingredientName = ingredientDto.getIngredientName();
        this.weight = ingredientDto.getWeight();
    }

    public static Ingredient createIngredient(Integer sequence, String materialName, Float weight) {
        return new Ingredient(sequence, materialName, weight);
    }

    public static Ingredient createIngredient(FurnaceProcess furnaceProcess, Integer sequence, String ingredientName, Float weight) {
        return new Ingredient(null, furnaceProcess, sequence, ingredientName, weight);
    }
}
