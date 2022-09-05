package com.mirae.smartfactory.dto.process.casting;

import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CastingListDto {

    private List<CastingQueryDto> castings;

    public CastingListDto(List<CastingQueryDto> castings) {
        this.castings = castings;
    }
}
