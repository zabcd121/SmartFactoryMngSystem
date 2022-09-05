package com.mirae.smartfactory.dto.process.furnace;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class FurnaceProcessListDto {

    private List<FurnaceProcessQueryDto> furnaceProcesses;

    public FurnaceProcessListDto(List<FurnaceProcessQueryDto> furnaceProcesses) {
        this.furnaceProcesses = furnaceProcesses;
    }
}
