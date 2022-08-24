package com.mirae.smartfactory.domain.process.furnace;

import com.mirae.smartfactory.domain.resource.ResourceType;
import com.mirae.smartfactory.dto.process.furnace.IngredientDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {

    @Enumerated(value = EnumType.STRING)
    private final ResourceType resourceType = ResourceType.INGREDIENT;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ingredientAnalysisId;

//    private int sequenceNumber;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "furnaceProcessId")
    private FurnaceProcess furnaceProcess;

    private String ingredientName;

    private Float weight;

    public Ingredient(String ingredientName, Float weight) {
        this.ingredientName = ingredientName;
        this.weight = weight;
    }

    public static Ingredient createIngredientWithDto(IngredientDto ingredientDto) {
        return Ingredient.createIngredient(ingredientDto.getIngredientName(), ingredientDto.getWeight());
    }

    public static Ingredient createIngredient(String materialName, Float weight) {
        return new Ingredient(materialName, weight);
    }
}
