package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.dto.PkDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessDto;
import com.mirae.smartfactory.dto.process.furnace.FurnaceProcessListDto;
import com.mirae.smartfactory.dto.result.SuccessNoResult;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.application.service.FurnaceProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.mirae.smartfactory.consts.ConditionCode.*;

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

        return new SuccessResult<>(FURNACEPROCESS_SEARCH_SUCCESS, result);
    }

    @PostMapping("/furnaceprocess")
    public SuccessResult<PkDto> furnaceProcessSave(@RequestBody FurnaceProcessDto furnaceProcessDto) {

        Long savedFurnaceProcessId = furnaceProcessService.saveFurnaceProcess(furnaceProcessDto);

        return new SuccessResult<>(FURNACEPROCESS_SAVE_SUCCESS, new PkDto(savedFurnaceProcessId));
    }

    @PutMapping("/furnaceprocess/{id}")
    public SuccessResult<PkDto> furnaceProcessUpdate(@PathVariable("id") Long furnaceProcessId,
                                     @RequestBody FurnaceProcessDto furnaceProcessDto) {

        Long updatedFurnaceProcessId = furnaceProcessService.updateFurnaceProcess(furnaceProcessId, furnaceProcessDto);

        return new SuccessResult<>(FURNACEPROCESS_UPDATE_SUCCESS, new PkDto(updatedFurnaceProcessId));
    }

    @DeleteMapping("/process/{id}")
    public SuccessNoResult furnaceProcessDelete(@PathVariable("id") Long processId) {
        furnaceProcessService.deleteFurnaceProcess(processId);

        return new SuccessNoResult(FURNACEPROCESS_DELETE_SUCCESS);
    }

}
