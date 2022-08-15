package com.mirae.smartfactory.dto;

import com.mirae.smartfactory.domain.furnace.AlloyCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProcessDTO {
    private long processId;
    private LocalDateTime date;
    private int dailyProcessId;
    private int furnaceNumber;
    private AlloyCode alloyCode;
    private int size;
}
