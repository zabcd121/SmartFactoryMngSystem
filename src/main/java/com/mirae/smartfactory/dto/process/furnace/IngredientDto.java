package com.mirae.smartfactory.dto.process.furnace;

import com.mirae.smartfactory.domain.model.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.model.process.furnace.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

    private Long ingredientAnalysisId;
    private Integer sequence;
    private String ingredientName;
    private Float weight;

    public static IngredientDto of(Ingredient ingredient) {
        return new IngredientDto(ingredient.getIngredientAnalysisId(), ingredient.getSequence(), ingredient.getIngredientName(), ingredient.getWeight());
    }

    public static Ingredient toEntity(IngredientDto ingredientWithIdDto) {
        return Ingredient.createIngredient(ingredientWithIdDto.getSequence(), ingredientWithIdDto.getIngredientName(), ingredientWithIdDto.getWeight());
    }

    public static Ingredient toEntity(IngredientDto ingredientWithIdDto, FurnaceProcess furnaceProcess) {
        return Ingredient.createIngredient(furnaceProcess, ingredientWithIdDto.getSequence(), ingredientWithIdDto.getIngredientName(), ingredientWithIdDto.getWeight());
    }
}
