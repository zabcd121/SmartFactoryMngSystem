package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.resource.MaterialName;
import com.mirae.smartfactory.domain.resource.MaterialType;
import com.mirae.smartfactory.dto.OuterScrapNameListDto;
import com.mirae.smartfactory.dto.SiNameListDto;
import com.mirae.smartfactory.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MaterialService {

    private final ResourceRepository materialRepository;

    @Transactional
    public OuterScrapNameListDto saveOuterScrapName(String newOuterScrapName) {
        materialRepository.save_outer(MaterialName.createMaterialName(MaterialType.OUTER, newOuterScrapName));

        return findOuterScrapList();
    }

    @Transactional
    public SiNameListDto saveSiName(String newSiName) {
        materialRepository.save_si(MaterialName.createMaterialName(MaterialType.SI, newSiName));

        return findSiList();
    }

    public OuterScrapNameListDto findOuterScrapList() {
        List<MaterialName> allOuter = materialRepository.findAll_outer();
        OuterScrapNameListDto outerScrapNameListDto = new OuterScrapNameListDto();

        allOuter.stream().forEach(m -> {
            outerScrapNameListDto.addOuterScrap(m.getKrMaterialName());
        });

        return outerScrapNameListDto;
    }

    public SiNameListDto findSiList() {
        List<MaterialName> allSi = materialRepository.findAll_si();
        SiNameListDto siNameListDto = new SiNameListDto();

        allSi.stream().forEach(m -> {
            siNameListDto.addSi(m.getKrMaterialName());
        });

        return siNameListDto;
    }
}
