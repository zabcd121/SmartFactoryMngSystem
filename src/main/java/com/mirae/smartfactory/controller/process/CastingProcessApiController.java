package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.dto.process.casting.CastingDto;
import com.mirae.smartfactory.dto.process.casting.CastingListDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.service.CastingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@RestController
@RequestMapping("/mirae")
@RequiredArgsConstructor
public class CastingProcessApiController {

    private final CastingService castingService;

    @GetMapping("/casting")
    public CastingListDto castingList(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return castingService.findCastings(date);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @PostMapping("/casting")
    public HttpHeaders furnaceProcessSave(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestBody CastingDto castingDto) throws URISyntaxException {
        castingService.saveCasting(castingDto);

        URI redirectUri = new URI("http://localhost:8080/mirae/casting?date=" + date);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return httpHeaders;
    }
}
