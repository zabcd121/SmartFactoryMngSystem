package com.mirae.smartfactory.controller.statistics;

import com.mirae.smartfactory.consts.DomainConditionCode;
import com.mirae.smartfactory.domain.statistics.Statistics;
import com.mirae.smartfactory.domain.statistics.StatisticsType;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.service.statistics.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics/material")
@RequiredArgsConstructor
public class MaterialStatisticsApiController {
    private final StatisticsService statisticsService;
    private static final StatisticsType statisticsType = StatisticsType.MATERIAL;

    @GetMapping("/daily")
    public SuccessResult<Statistics> materialDailyStatistics(){

        Statistics materialAmountForCurrent7Days = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(statisticsType);
        return new SuccessResult<>(DomainConditionCode.MATERIAL_DAILY_SEARCH_SUCCESS, "ok", materialAmountForCurrent7Days);
    }

    @GetMapping("/monthly")
    public SuccessResult<Statistics> materialMonthlyStatistics(){
        Statistics materialAmountForCurrent12Months = statisticsService.findMonthlyAvgAndDeviationDataForCurrent12Months(statisticsType);
        return new SuccessResult<>(DomainConditionCode.MATERIAL_MONTHLY_SEARCH_SUCCESS, "ok", materialAmountForCurrent12Months);
    }

    @GetMapping("/quarterly")
    public SuccessResult<Statistics> materialQuarterlyStatistics(){
        Statistics materialAmountForCurrent4Quarter = statisticsService.findAvgAndDeviationDataForCurrent4Quarter(statisticsType);
        return new SuccessResult<>(DomainConditionCode.MATERIAL_QUARTERLY_SEARCH_SUCCESS, "ok", materialAmountForCurrent4Quarter);
    }

    @GetMapping("/yearly")
    public SuccessResult<Statistics> materialYearlyStatistics(){
        Statistics materialAmountForCurrent4Years = statisticsService.findAvgAndDeviationDataForCurrent4Years(statisticsType);
        return new SuccessResult<>(DomainConditionCode.MATERIAL_YEARLY_SEARCH_SUCCESS, "ok", materialAmountForCurrent4Years);
    }
}
