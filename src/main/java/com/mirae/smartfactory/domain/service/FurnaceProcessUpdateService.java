package com.mirae.smartfactory.domain.service;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.domain.model.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.model.process.furnace.Ingredient;
import com.mirae.smartfactory.domain.model.resource.Additive;
import com.mirae.smartfactory.domain.model.resource.Material;
import com.mirae.smartfactory.domain.repository.IFurnaceProcessRepository;
import com.mirae.smartfactory.domain.repository.IProcessRepository;
import com.mirae.smartfactory.dto.process.ProcessDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.IngredientDto;
import com.mirae.smartfactory.dto.resource.AdditiveDto;
import com.mirae.smartfactory.dto.resource.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FurnaceProcessUpdateService {
    private final IFurnaceProcessRepository fpRepository;
    private final IProcessRepository processRepository;

    //기존에 용해일지에 저장했었던 Resource들 중 update 요청에는 빠져있는 것들은 프론트에서 삭제버튼으로 삭제된 것 이므로 사전에 삭제 처리
//    public void deleteRemovedResources(FurnaceProcess fp, FurnaceProcessDto fpDto) {
//        List<Additive> removedAdditives = traceRemovedAdditives(fp.getProcess().getAdditives(), fpDto.getProcess().getAdditives());
//        List<Material> removedMaterials = traceRemovedMaterials(fp.getProcess().getMaterials(), fpDto.getProcess().getMaterials());
//        List<Ingredient> removedIngredients = traceRemovedIngredients(fp.getIngredients(), fpDto.getIngredients());
//
//        deleteRemovedAdditives(removedAdditives);
//        deleteRemovedMaterials(removedMaterials);
//        deleteRemovedIngredients(removedIngredients);
//    }

    public void updateResources(FurnaceProcess fp, FurnaceProcessDto fpDto) {
        List<Ingredient> ingredientList = fpRepository.findIngredientList(fp.getFurnaceProcessId());
        List<Material> materialList = processRepository.findMaterialList(fp.getProcess().getProcessId());
        List<Additive> additiveList = processRepository.findAdditiveList(fp.getProcess().getProcessId());

        updateIngredients(fp, fpDto, ingredientList);
        updateMaterials(fp.getProcess(), fpDto.getProcess(), materialList);
        updateAdditives(fp.getProcess(), fpDto.getProcess(), additiveList);
    }



    private void updateIngredients(FurnaceProcess fp, FurnaceProcessDto fpDto, List<Ingredient> ingredientList) {
        for (IngredientDto ingredientDto : fpDto.getIngredients()) {
            for (Ingredient ingredient : ingredientList) {
                if(ingredient.getIngredientAnalysisId() == ingredientDto.getIngredientAnalysisId()) {
                    ingredient.changeState(ingredientDto);
                    break;
                }
            }
        }

        for(IngredientDto ingredientDto : fpDto.getIngredients()) {
            if(ingredientDto.getIngredientAnalysisId() == null) {
                fpRepository.saveIngredient(IngredientDto.toEntity(ingredientDto, fp));
            }
        }
    }

    private void updateMaterials(Process process, ProcessDto processDto, List<Material> materialList) {
        for (MaterialDto materialDto : processDto.getMaterials()) {
            for (Material material : materialList) {
                if(material.getMaterialId() == materialDto.getMaterialId()) {
                    material.changeState(materialDto);
                    break;
                }
            }
        }

        for (MaterialDto materialDto : processDto.getMaterials()) {
            if (materialDto.getMaterialId() == null) {
                processRepository.saveMaterial(MaterialDto.toEntity(materialDto, process));
            }
        }
    }

    private void updateAdditives(Process process, ProcessDto processDto, List<Additive> additiveList) {
        for (AdditiveDto additiveDto : processDto.getAdditives()) {
            for (Additive additive : additiveList) {
                if(additive.getAdditiveId() == additiveDto.getAdditiveId()) {
                    additive.changeState(additiveDto);
                    break;
                }
            }
        }

        for (AdditiveDto additiveDto : processDto.getAdditives()) {
            if (additiveDto.getAdditiveId() == null) {
                processRepository.saveAdditive(AdditiveDto.toEntity(additiveDto, process));
            }
        }
    }

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

    public List<Ingredient> traceRemovedIngredients(List<Ingredient> ingredients, List<IngredientDto> ingredientDtos) {

        List<Ingredient> result = ingredients.stream()
                .filter(ingredient ->
                    ingredientDtos.stream().noneMatch(ingredientDto -> ingredient.getIngredientAnalysisId().equals(ingredientDto.getIngredientAnalysisId()))
                ).collect(Collectors.toList());

        return result;
    }

    public List<Material> traceRemovedMaterials(List<Material> materials, List<MaterialDto> materialDtos) {
        List<Material> result = materials.stream()
                .filter(material ->
                        materialDtos.stream().noneMatch(materialDto -> material.getMaterialId().equals(materialDto.getMaterialId()))
                ).collect(Collectors.toList());

        return result;
    }

    public List<Additive> traceRemovedAdditives(List<Additive> additives, List<AdditiveDto> additiveDtos) {
        List<Additive> result = additives.stream()
                .filter(material ->
                        additiveDtos.stream().noneMatch(materialDto -> material.getAdditiveId().equals(materialDto.getAdditiveId()))
                ).collect(Collectors.toList());

        return result;
    }
}
