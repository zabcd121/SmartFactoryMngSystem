package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.application.service.CastingService;
import com.mirae.smartfactory.domain.model.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.dto.BilletDto;
import com.mirae.smartfactory.dto.PkDto;
import com.mirae.smartfactory.dto.process.casting.*;
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
    private final CastingService castingService;

    @GetMapping("/furnaceprocess")
    public SuccessResult<FurnaceProcessListDto> furnaceProcessList(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        FurnaceProcessListDto result = furnaceProcessService.findFurnaceProcesses(date);

        return new SuccessResult<>(FURNACEPROCESS_SEARCH_SUCCESS, result);
    }

    @PostMapping("/furnaceprocess")
    public SuccessResult<PkDto> furnaceProcessSave(@RequestBody FurnaceProcessDto furnaceProcessDto) {

        FurnaceProcess savedFurnaceProcess = furnaceProcessService.saveFurnaceProcess(furnaceProcessDto);
        CastingDto emptyCastingDto = CastingDto.builder()
                .castingId(null)
                .tappingStartTime(null)
                .tappingEndTime(null)
                .operator(null)
                .shifter(null)
                .remarks(null)
                .process(ProcessSimpleDto.builder()
                        .processId(savedFurnaceProcess.getProcess().getProcessId())
                        .date(furnaceProcessDto.getProcess().getDate())
                        .dailyProcessId(furnaceProcessDto.getProcess().getDailyProcessId())
                        .furnaceNumber(furnaceProcessDto.getProcess().getFurnaceNumber())
                        .alloyCode(furnaceProcessDto.getProcess().getAlloyCode())
                        .size(furnaceProcessDto.getProcess().getSize())
                        .memberId(furnaceProcessDto.getProcess().getMemberId())
                        .build())
                .castingPreparation(CastingPreparationDto.builder()
                        .castingPreparationId(null)
                        .rotorSpeed1(null)
                        .rotorSpeed2(null)
                        .arAmount1(null)
                        .arAmount2(null)
                        .operatingDuration(null)
                        .outGassingTemperature(null)
                        .atbSpeed(null)
                        .castingPreparationMore(null)
                        .build())
                .castingData(CastingDataDto.builder()
                        .castingDataId(null)
                        .castingSpeedInit(null)
                        .castingSpeedLampInit(null)
                        .castingSpeedNormal(null)
                        .castingSpeedLampStop(null)
                        .castingSpeedTerminated(null)
                        .castingTemperature0(null)
                        .castingTemperature500(null)
                        .castingTemperature1000(null)
                        .castingTemperature2000(null)
                        .castingTemperature3000(null)
                        .castingTemperature4000(null)
                        .castingTemperature5000(null)
                        .castingTemperature5500(null)
                        .castingTemperature6000(null)
                        .castingTemperature6500(null)
                        .castingTemperature7000(null)
                        .coolingWaterInit(null)
                        .coolingWaterLampInit(null)
                        .coolingWaterNormal(null)
                        .coolingWaterLampStop(null)
                        .coolingWaterTerminated(null)
                        .castingOilPressure(null)
                        .castingOilCycle(null)
                        .castingOilOnTime(null)
                        .castingOilOffTime(null)
                        .gasPressureNeutrality(null)
                        .gasPressureInit(null)
                        .gasPressureNormal(null)
                        .sustainTime(null)
                        .build())
                .castingTemperature(CastingTemperatureDto.builder()
                        .castingTemperatureId(null)
                        .furnaceTemperatureInit(null)
                        .furnaceTemperature15M(null)
                        .furnaceTemperature30M(null)
                        .furnaceTemperature45M(null)
                        .furnaceTemperature60M(null)
                        .furnaceTemperature75M(null)
                        .furnaceTemperature90M(null)
                        .coolingWaterTemperatureInit(null)
                        .coolingWaterTemperatureMiddle(null)
                        .coolingWaterTemperatureTerminated(null)
                        .build())
                .billet(BilletDto.builder()
                        .billetId(null)
                        .length(null)
                        .quantity(null)
                        .errorQuantity(null)
                        .errorReason(null)
                        .build())
                .build();
        castingService.saveCasting(emptyCastingDto);
        return new SuccessResult<>(FURNACEPROCESS_SAVE_SUCCESS, new PkDto(savedFurnaceProcess.getFurnaceProcessId()));
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
