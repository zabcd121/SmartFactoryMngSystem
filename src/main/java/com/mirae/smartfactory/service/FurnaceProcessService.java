package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.process.furnace.Ingredient;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessListDto;
import com.mirae.smartfactory.repository.FurnaceProcessRepository;
import com.mirae.smartfactory.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FurnaceProcessService {

    private final FurnaceProcessRepository furnaceProcessRepository;
    private final MemberRepository memberRepository;

//    @Transactional
//    public void saveFurnaceProcess(FurnaceProcessDto furnaceProcessDto) {
//        Process process = Process.createProcessWithDto(furnaceProcessDto.getProcess(), memberRepository.findById(furnaceProcessDto.getProcess().getMemberId()));
//        Ingredient ingredientAnalysis = Ingredient.createIngredientAnalysisWithDto(furnaceProcessDto.getIngredientAnalysis());
//
//        FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcessWithDto(furnaceProcessDto, process, ingredientAnalysis);
//
//        furnaceProcessRepository.save(furnaceProcess);
//    }

    @Transactional
    public void saveFurnaceProcess(FurnaceProcessDto furnaceProcessDto) {
        Process process = Process.createProcessWithDto(furnaceProcessDto.getProcess(), memberRepository.findById(furnaceProcessDto.getProcess().getMemberId()));

        FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcessWithDto(furnaceProcessDto, process);

        furnaceProcessRepository.save(furnaceProcess);
    }

    public FurnaceProcessListDto findFurnaceProcesses(LocalDate date) {
        List<FurnaceProcess> furnaceProcesses = furnaceProcessRepository.findListByDate(date);

        List<FurnaceProcessDto> furnaceProcessDtos = furnaceProcesses.stream()
                .map(fp -> new FurnaceProcessDto(fp))
                .collect(Collectors.toList());

        FurnaceProcessListDto result = new FurnaceProcessListDto(furnaceProcessDtos);

        return result;
    }


}
