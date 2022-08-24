package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessListDto;
import com.mirae.smartfactory.repository.FurnaceProcessRepository;
import com.mirae.smartfactory.service.FurnaceProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/mirae")
@RequiredArgsConstructor
public class FurnaceProcessApiController {

    private final FurnaceProcessRepository furnaceProcessRepository;
    private final FurnaceProcessService furnaceProcessService;

    @GetMapping("/furnaceprocess")
    public FurnaceProcessListDto furnaceProcessList(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate date
    ) {
        return furnaceProcessService.findFurnaceProcesses(date);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @PostMapping("/furnaceprocess")
    public HttpHeaders furnaceProcessSave(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate date, @RequestBody FurnaceProcessDto furnaceProcessDto) throws URISyntaxException {
        furnaceProcessService.saveFurnaceProcess(furnaceProcessDto);

        URI redirectUri = new URI("http://localhost:8080/mirae/furnaceprocess?date=" + date);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return httpHeaders;
    }

}
