package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessListDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessQueryDto;
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

    @Transactional
    public Long saveFurnaceProcess(FurnaceProcessDto furnaceProcessDto) {
        Process process = Process.createProcessWithDto(furnaceProcessDto.getProcess(), memberRepository.findById(furnaceProcessDto.getProcess().getMemberId()));

        FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcessWithDto(furnaceProcessDto, process);

        furnaceProcessRepository.save(furnaceProcess);

        return furnaceProcess.getFurnaceProcessId();
    }

    @Transactional
    public Long updateFurnaceProcess(Long furnaceProcessId, FurnaceProcessDto furnaceProcessDto) {
        Process process = Process.createProcessWithDto(furnaceProcessDto.getProcess(), memberRepository.findById(furnaceProcessDto.getProcess().getMemberId()));

        FurnaceProcess furnaceProcess = FurnaceProcess.createFurnaceProcessWithDtoAndId(furnaceProcessId, furnaceProcessDto, process);

        furnaceProcessRepository.update(furnaceProcess);

        return furnaceProcess.getFurnaceProcessId();
    }

    public FurnaceProcessListDto findFurnaceProcesses(LocalDate date) {
        List<FurnaceProcess> furnaceProcesses = furnaceProcessRepository.findListByDate(date);

        List<FurnaceProcessQueryDto> furnaceProcessDtos = furnaceProcesses.stream()
                .map(fp -> new FurnaceProcessQueryDto(fp))
                .collect(Collectors.toList());

        FurnaceProcessListDto result = new FurnaceProcessListDto(furnaceProcessDtos);

        return result;
    }


}
