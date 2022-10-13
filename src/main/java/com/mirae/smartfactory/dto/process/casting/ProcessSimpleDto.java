package com.mirae.smartfactory.dto.process.casting;

import com.mirae.smartfactory.domain.model.process.Process;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessSimpleDto {
    private Long processId;
    private LocalDate date;

    private Integer dailyProcessId;

    private Integer furnaceNumber;

    private Integer alloyCode;

    private Integer size;

    private Long memberId;

    public ProcessSimpleDto(Process process) {
        this.processId = process.getProcessId();
        this.date = process.getDate();
        this.dailyProcessId = process.getDailyProcessId();
        this.furnaceNumber = process.getFurnaceNumber();
        this.alloyCode = process.getAlloyCode();
        this.size = process.getSize();
        this.memberId = process.getMember().getMemberId();
    }
}
