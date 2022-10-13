package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.dto.PkDto;
import com.mirae.smartfactory.dto.process.casting.CastingDto;
import com.mirae.smartfactory.dto.process.casting.CastingListDto;
import com.mirae.smartfactory.dto.result.SuccessNoResult;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.application.service.CastingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.mirae.smartfactory.consts.ConditionCode.*;


@RestController
@RequestMapping("/mirae")
@RequiredArgsConstructor
public class CastingProcessApiController {

    private final CastingService castingService;

    @GetMapping("/casting")
    public SuccessResult<CastingListDto> castingList(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        CastingListDto castings = castingService.findCastings(date);
        return new SuccessResult<>(CASTING_SEARCH_SUCCESS, castings);
    }

    @PostMapping("/casting")
    public SuccessResult<PkDto> castingSave(@RequestBody CastingDto castingDto) {

        Long castingId = castingService.saveCasting(castingDto);

        return new SuccessResult<>(CASTING_SAVE_SUCCESS, new PkDto(castingId));
    }

    @PutMapping("/casting/{id}")
    public SuccessResult<PkDto> castingUpdate(
            @PathVariable("id") Long castingId,
            @RequestBody CastingDto castingDto) {

        Long updatedCastingId = castingService.updateCasting(castingId, castingDto);
        System.out.println("#############updateCastingId" + updatedCastingId);

        return new SuccessResult<>(CASTING_UPDATE_SUCCESS, new PkDto(updatedCastingId));

    }

    @DeleteMapping("/casting/{id}")
    public SuccessNoResult castingDelete(@PathVariable("id") Long castingId) {

        castingService.deleteCasting(castingId);

        return new SuccessNoResult(CASTING_DELETE_SUCCESS);
    }
}
