package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.consts.DomainConditionCode;
import com.mirae.smartfactory.consts.RedirectURLs;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessListDto;
import com.mirae.smartfactory.dto.result.SuccessNoResult;
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
    private final FurnaceProcessService furnaceProcessService;

    @GetMapping("/furnaceprocess")
    public SuccessResult<FurnaceProcessListDto> furnaceProcessList(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        FurnaceProcessListDto result = furnaceProcessService.findFurnaceProcesses(date);

        return new SuccessResult<>(FURNACEPROCESS_SEARCH_SUCCESS, "ok", result);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @PostMapping("/furnaceprocess")
    public void furnaceProcessSave(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                   @RequestBody FurnaceProcessDto furnaceProcessDto,
                                   HttpServletResponse httpServletResponse) {
        furnaceProcessService.saveFurnaceProcess(furnaceProcessDto);

        httpServletResponse.addHeader("Location", RedirectURLs.FURNACE_PROCESS_REDIRECT_URL + date);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @PutMapping("/furnaceprocess/{id}")
    public void furnaceProcessUpdate(@PathVariable("id") Long furnaceProcessId,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                     @RequestBody FurnaceProcessDto furnaceProcessDto,
                                     HttpServletResponse httpServletResponse) {
        furnaceProcessService.updateFurnaceProcess(furnaceProcessId, furnaceProcessDto);

        httpServletResponse.addHeader("Location", RedirectURLs.FURNACE_PROCESS_REDIRECT_URL + date);
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @DeleteMapping("/process/{id}")
    public SuccessNoResult furnaceProcessDelete(@PathVariable("id") Long processId,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                HttpServletResponse httpServletResponse) {
        furnaceProcessService.deleteFurnaceProcess(processId);

        return new SuccessNoResult(FURNACEPROCESS_DELETE_SUCCESS, "용해일지 삭제 성공");
    }

}
