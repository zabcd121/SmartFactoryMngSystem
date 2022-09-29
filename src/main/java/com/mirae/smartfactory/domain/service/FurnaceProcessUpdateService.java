package com.mirae.smartfactory.domain.service;

import com.mirae.smartfactory.domain.model.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.model.process.furnace.Ingredient;
import com.mirae.smartfactory.domain.model.resource.Additive;
import com.mirae.smartfactory.domain.model.resource.Material;
import com.mirae.smartfactory.domain.repository.IFurnaceProcessRepository;
import com.mirae.smartfactory.domain.repository.IProcessRepository;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.IngredientDto;
import com.mirae.smartfactory.dto.resource.AdditiveDto;
import com.mirae.smartfactory.dto.resource.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FurnaceProcessUpdateService {
    private final IFurnaceProcessRepository fpRepository;
    private final IProcessRepository processRepository;

    public void deleteRemovedIngredients(List<Ingredient> shouldRemoveIngredients){
//        FurnaceProcess fp = fpRepository.findById(fpDto.getFurnaceProcessId()).orElseGet(null);

        for (Ingredient shouldRemoveIngredient : shouldRemoveIngredients) {
            fpRepository.deleteIngredient(shouldRemoveIngredient);
        }
    }

    public void deleteRemovedMaterials(List<Material> shouldRemoveMaterials){
//        FurnaceProcess fp = fpRepository.findById(fpDto.getFurnaceProcessId()).orElseGet(null);

        for (Material shouldRemoveMaterial : shouldRemoveMaterials) {
            processRepository.deleteMaterial(shouldRemoveMaterial);
        }
    }

    public void deleteRemovedAdditives(List<Additive> shouldRemoveAdditives){
//        FurnaceProcess fp = fpRepository.findById(fpDto.getFurnaceProcessId()).orElseGet(null);

        for (Additive shouldRemoveAdditive : shouldRemoveAdditives) {
            processRepository.deleteAdditive(shouldRemoveAdditive);
        }
    }

    public List<Ingredient> traceDeletedIngredients(List<Ingredient> ingredients, List<IngredientDto> ingredientDtos) {

        List<Ingredient> result = ingredients.stream()
                .filter(ingredient ->
                    ingredientDtos.stream().noneMatch(ingredientDto -> ingredient.getIngredientAnalysisId().equals(ingredientDto.getIngredientAnalysisId()))
                ).collect(Collectors.toList());

        return result;
    }

    public List<Material> traceDeletedMaterials(List<Material> materials, List<MaterialDto> materialDtos) {
        List<Material> result = materials.stream()
                .filter(material ->
                        materialDtos.stream().noneMatch(materialDto -> material.getMaterialId().equals(materialDto.getMaterialId()))
                ).collect(Collectors.toList());

        return result;
    }

    public List<Additive> traceDeletedAdditives(List<Additive> additives, List<AdditiveDto> additiveDtos) {
        List<Additive> result = additives.stream()
                .filter(material ->
                        additiveDtos.stream().noneMatch(materialDto -> material.getAdditiveId().equals(materialDto.getAdditiveId()))
                ).collect(Collectors.toList());

        return result;
    }
}
