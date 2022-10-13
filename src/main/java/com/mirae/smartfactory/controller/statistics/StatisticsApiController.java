package com.mirae.smartfactory.controller.statistics;

import com.mirae.smartfactory.consts.ConditionCode;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.dto.statistics.DailyStatisticsListDto;
import com.mirae.smartfactory.application.service.TotalStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mirae.smartfactory.consts.ConditionCode.*;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsApiController {

    private final TotalStatisticsService totalStatisticsService;

    @GetMapping("/daily")
    public SuccessResult<DailyStatisticsListDto> totalDailyStatistics() {
        DailyStatisticsListDto totalDailyStatistics = totalStatisticsService.findTotalDomainDailyStatistics();
        return new SuccessResult<>(TOTAL_DAILY_SEARCH_SUCCESS.getCode(), TOTAL_DAILY_SEARCH_SUCCESS.getMessage(), totalDailyStatistics);
    }
}
