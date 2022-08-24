package com.mirae.smartfactory.dto.process.casting;

import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.dto.AdditiveDto;
import com.mirae.smartfactory.dto.MaterialDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class ProcessSimpleDto {
    private LocalDate date;

    private Integer dailyProcessId;

    private Integer furnaceNumber;

    private Integer alloyCode;

    private Integer size;

    private Long memberId;

    public ProcessSimpleDto(Process process) {
        this.date = process.getDate();
        this.dailyProcessId = process.getDailyProcessId();
        this.furnaceNumber = process.getFurnaceNumber();
        this.alloyCode = process.getAlloyCode();
        this.size = process.getSize();
        this.memberId = process.getMember().getMemberId();
    }
}
