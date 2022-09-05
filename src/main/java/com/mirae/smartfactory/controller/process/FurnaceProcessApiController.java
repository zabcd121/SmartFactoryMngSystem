package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessListDto;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.repository.FurnaceProcessRepository;
import com.mirae.smartfactory.service.FurnaceProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static com.mirae.smartfactory.consts.DomainConditionCode.*;

@Slf4j
@RestController
@RequestMapping("/mirae")
@RequiredArgsConstructor
public class FurnaceProcessApiController {

    private final FurnaceProcessRepository furnaceProcessRepository;
    private final FurnaceProcessService furnaceProcessService;

    @GetMapping("/furnaceprocess")
    public SuccessResult<FurnaceProcessListDto> furnaceProcessList(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        FurnaceProcessListDto result = furnaceProcessService.findFurnaceProcesses(date);

        return new SuccessResult<FurnaceProcessListDto>(FURNACEPROCESS_SEARCH_SUCCESS, "ok", result);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @PostMapping("/furnaceprocess")
    public void furnaceProcessSave(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                   @RequestBody FurnaceProcessDto furnaceProcessDto,
                                   HttpServletResponse httpServletResponse) {
        furnaceProcessService.saveFurnaceProcess(furnaceProcessDto);

        httpServletResponse.addHeader("Location", "http://localhost:8080/mirae/furnaceprocess?date=" + date);
//        return new SuccessResult<String>(FURNACEPROCESS_SAVE_SUCCESS, "저장 완료 되었습니다.", id.toString());
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @PutMapping("/furnaceprocess/{id}")
    public void furnaceProcessUpdate(@PathVariable("id") Long furnaceProcessId,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                     @RequestBody FurnaceProcessDto furnaceProcessDto,
                                     HttpServletResponse httpServletResponse) {
        furnaceProcessService.updateFurnaceProcess(furnaceProcessId, furnaceProcessDto);

        httpServletResponse.addHeader("Location", "http://localhost:8080/mirae/furnaceprocess?date=" + date);
//        return new SuccessResult<String>(FURNACEPROCESS_SAVE_SUCCESS, "저장 완료 되었습니다.", id.toString());
    }

}
