package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.consts.DomainConditionCode;
import com.mirae.smartfactory.consts.RedirectURLs;
import com.mirae.smartfactory.dto.process.casting.CastingDto;
import com.mirae.smartfactory.dto.process.casting.CastingListDto;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.service.CastingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static com.mirae.smartfactory.consts.DomainConditionCode.*;

@RestController
@RequestMapping("/mirae")
@RequiredArgsConstructor
public class CastingProcessApiController {

    private final CastingService castingService;

    @GetMapping("/casting")
    public SuccessResult<CastingListDto> castingList(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        CastingListDto castings = castingService.findCastings(date);
        return new SuccessResult<>(CASTING_SEARCH_SUCCESS, "ok", castings);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @PostMapping("/casting")
    public void castingSave(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestBody CastingDto castingDto,
            HttpServletResponse httpServletResponse){

        castingService.saveCasting(castingDto);
        httpServletResponse.addHeader("Location", RedirectURLs.CASTING_REDIRECT_URL + date);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @PutMapping("/casting/{id}")
    public void castingUpdate(
            @PathVariable("id") Long castingId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestBody CastingDto castingDto,
            HttpServletResponse httpServletResponse){

        castingService.updateCasting(castingId, castingDto);
        httpServletResponse.addHeader("Location", RedirectURLs.CASTING_REDIRECT_URL + date);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @DeleteMapping("/casting/{id}")
    public void castingDelete(
            @PathVariable("id") Long castingId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            HttpServletResponse httpServletResponse){

        castingService.deleteCasting(castingId);
        httpServletResponse.addHeader("Location", RedirectURLs.CASTING_REDIRECT_URL + date);
    }
}
