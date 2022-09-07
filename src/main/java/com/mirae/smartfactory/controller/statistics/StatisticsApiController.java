package com.mirae.smartfactory.controller.statistics;

import com.mirae.smartfactory.consts.DomainConditionCode;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.dto.statistics.DailyStatisticsListDto;
import com.mirae.smartfactory.service.statistics.TotalStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsApiController {

    private final TotalStatisticsService totalStatisticsService;

    @GetMapping("/daily")
    public SuccessResult<DailyStatisticsListDto> totalDailyStatistics() {
        DailyStatisticsListDto totalDailyStatistics = totalStatisticsService.findTotalDomainDailyStatistics();
        return new SuccessResult<>(DomainConditionCode.TOTAL_DAILY_SEARCH_SUCCESS, "ok", totalDailyStatistics);
    }
}
