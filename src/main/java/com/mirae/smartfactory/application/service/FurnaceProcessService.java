package com.mirae.smartfactory.application.service;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.domain.model.process.casting.Casting;
import com.mirae.smartfactory.domain.model.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.model.process.furnace.Ingredient;
import com.mirae.smartfactory.domain.model.resource.Additive;
import com.mirae.smartfactory.domain.model.resource.Material;
import com.mirae.smartfactory.domain.model.resource.Member;
import com.mirae.smartfactory.domain.service.FurnaceProcessUpdateService;
import com.mirae.smartfactory.dto.process.ProcessDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessListDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.IngredientDto;
import com.mirae.smartfactory.exception.NotExistException;
import com.mirae.smartfactory.repository.CastingRepository;
import com.mirae.smartfactory.repository.FurnaceProcessRepository;
import com.mirae.smartfactory.repository.MemberRepository;
import com.mirae.smartfactory.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FurnaceProcessService {

    private final FurnaceProcessUpdateService fpUpdateService;

    private final FurnaceProcessRepository fpRepository;
    private final ProcessRepository processRepository;
    private final CastingRepository castingRepository;
    private final MemberRepository memberRepository;

//    @Transactional
//    public Long saveFurnaceProcess(FurnaceProcessDto furnaceProcessDto) {
//        Member member = memberRepository.findById(furnaceProcessDto.getProcess().getMemberId());
//
//        Process process = ProcessDto.toEntity(furnaceProcessDto.getProcess(), member);
//        FurnaceProcess furnaceProcess = FurnaceProcessDto.toEntity(furnaceProcessDto, process);
//
//        for (Ingredient ingredient : furnaceProcess.getIngredients()) {
//            fpRepository.saveIngredient(ingredient);
//        }
//
//        for (Additive additive : process.getAdditives()) {
//            processRepository.saveAdditive(additive);
//        }
//
//        for (Material material : process.getMaterials()) {
//            processRepository.saveMaterial(material);
//        }
//
//        fpRepository.save(furnaceProcess);
//
//        return furnaceProcess.getFurnaceProcessId();
//    }

    @Transactional
    public FurnaceProcess saveFurnaceProcess(FurnaceProcessDto furnaceProcessDto) {
        Member member = memberRepository.findById(furnaceProcessDto.getProcess().getMemberId());

        Process process = ProcessDto.toEntity(furnaceProcessDto.getProcess(), member);
        FurnaceProcess furnaceProcess = FurnaceProcessDto.toEntity(furnaceProcessDto, process);

        for (Ingredient ingredient : furnaceProcess.getIngredients()) {
            fpRepository.saveIngredient(ingredient);
        }

        for (Additive additive : process.getAdditives()) {
            processRepository.saveAdditive(additive);
        }

        for (Material material : process.getMaterials()) {
            processRepository.saveMaterial(material);
        }

        fpRepository.save(furnaceProcess);

        return furnaceProcess;
    }

    @Transactional
    public Long updateFurnaceProcess(Long furnaceProcessId, FurnaceProcessDto fpDto) {
        FurnaceProcess orgFp = fpRepository.findById(furnaceProcessId).orElseGet(null);
        orgFp.changeState(fpDto, fpUpdateService);

        return orgFp.getFurnaceProcessId();
    }

    @Transactional
    public void deleteFurnaceProcess(Long processId) {
        Casting findCasting = castingRepository.findByProcessId(processId).orElseGet(() -> null);
        if(findCasting != null) {
            castingRepository.delete(findCasting);
        }

        fpRepository.delete(
                fpRepository.findByProcessId(processId).orElseThrow(() -> new NotExistException("삭제불가능한 용해일지 ID 입니다."))
        );
    }

    public FurnaceProcessListDto findFurnaceProcesses(LocalDate date) {
        List<FurnaceProcess> furnaceProcesses = fpRepository.findListByDate(date);

        List<FurnaceProcessDto> furnaceProcessDtos = furnaceProcesses.stream()
                .map(fp -> new FurnaceProcessDto(fp))
                .collect(Collectors.toList());

        FurnaceProcessListDto result = new FurnaceProcessListDto(furnaceProcessDtos);

        return result;
    }


}
