package com.mirae.smartfactory.dto.process.furnace;

import lombok.Getter;

import java.util.List;

@Getter
public class FurnaceProcessListDto {

    private List<FurnaceProcessDto> furnaceProcesses;

    public FurnaceProcessListDto(List<FurnaceProcessDto> furnaceProcesses) {
        this.furnaceProcesses = furnaceProcesses;
    }
}
