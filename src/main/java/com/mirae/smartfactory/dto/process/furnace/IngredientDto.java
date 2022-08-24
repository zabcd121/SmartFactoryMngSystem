package com.mirae.smartfactory.dto.process.furnace;

import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.process.furnace.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IngredientDto {

    private String ingredientName;
    private Float weight;

    public IngredientDto(Ingredient ingredient) {
        this.ingredientName = ingredient.getIngredientName();
        this.weight = ingredient.getWeight();
//        this.furnaceProcessId = ingredient.getFurnaceProcess().getFurnaceProcessId();
    }
}
