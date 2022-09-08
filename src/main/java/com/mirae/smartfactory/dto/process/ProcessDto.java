package com.mirae.smartfactory.dto.process;

import com.mirae.smartfactory.dto.resource.AdditiveDto;
import com.mirae.smartfactory.dto.resource.MaterialDto;
import com.mirae.smartfactory.domain.process.Process;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
@EqualsAndHashCode(of = "processId")
@AllArgsConstructor
public class ProcessDto {
    private LocalDate date;

    private Integer dailyProcessId;

    private Integer furnaceNumber;

    private Integer alloyCode;

    private Integer size;

    private List<MaterialDto> materials;

    private List<AdditiveDto> additives;

    private Long memberId;

    public ProcessDto(Process process) {
        this.date = process.getDate();
        this.dailyProcessId = process.getDailyProcessId();
        this.furnaceNumber = process.getFurnaceNumber();
        this.alloyCode = process.getAlloyCode();
        this.size = process.getSize();
        this.materials = process.getMaterials().stream()
                .map(material -> new MaterialDto(material))
                .collect(Collectors.toList());
        this.additives = process.getAdditives().stream()
                .map(additive -> new AdditiveDto(additive))
                .collect(Collectors.toList());
        this.memberId = process.getMember().getMemberId();
    }
}
