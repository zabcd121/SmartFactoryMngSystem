package com.mirae.smartfactory.dto.process;

import com.mirae.smartfactory.domain.model.resource.Member;
import com.mirae.smartfactory.dto.resource.AdditiveDto;
import com.mirae.smartfactory.dto.resource.MaterialDto;
import com.mirae.smartfactory.domain.model.process.Process;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
@EqualsAndHashCode(of = "processId")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDto {
    private Long processId;
    private LocalDate date;

    private Integer dailyProcessId;

    private Integer furnaceNumber;

    private Integer alloyCode;

    private Integer size;

    private List<MaterialDto> materials;

    private List<AdditiveDto> additives;

    private Long memberId;

    private ProcessDto(Process process) {
        this.processId = process.getProcessId();
        this.date = process.getDate();
        this.dailyProcessId = process.getDailyProcessId();
        this.furnaceNumber = process.getFurnaceNumber();
        this.alloyCode = process.getAlloyCode();
        this.size = process.getSize();
        this.materials = process.getMaterials().stream()
                .map(material -> MaterialDto.of(material))
                .collect(Collectors.toList());
        this.additives = process.getAdditives().stream()
                .map(additive -> AdditiveDto.of(additive))
                .collect(Collectors.toList());
        this.memberId = process.getMember().getMemberId();
    }

    public static ProcessDto of(Process process) {
        return new ProcessDto(process);
    }

    public static Process toEntity(ProcessDto processDto, Member member) {
        return Process.createProcess(processDto.getDate(), processDto.getDailyProcessId(), processDto.furnaceNumber, processDto.alloyCode, processDto.getSize(),
                processDto.getMaterials().stream().map(materialDto -> MaterialDto.toEntity(materialDto)).collect(Collectors.toList()),
                processDto.getAdditives().stream().map(additive -> AdditiveDto.toEntity(additive)).collect(Collectors.toList()),
                member);
    }
}
