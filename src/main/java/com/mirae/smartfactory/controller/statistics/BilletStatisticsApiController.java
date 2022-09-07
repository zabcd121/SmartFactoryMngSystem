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
@RequestMapping("/statistics/billet")
@RequiredArgsConstructor
public class BilletStatisticsApiController {
    private final StatisticsService statisticsService;
    private static final StatisticsType statisticsType = StatisticsType.BILLET;

    @GetMapping("/daily")
    public SuccessResult<Statistics> billetDailyStatistics(){

        Statistics billetAmountForCurrent7Days = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(statisticsType);
        return new SuccessResult<>(DomainConditionCode.BILLET_DAILY_SEARCH_SUCCESS, "ok", billetAmountForCurrent7Days);
    }

    @GetMapping("/monthly")
    public SuccessResult<Statistics> billetMonthlyStatistics(){
        Statistics billetAmountForCurrent12Months = statisticsService.findMonthlyAvgAndDeviationDataForCurrent12Months(statisticsType);
        return new SuccessResult<>(DomainConditionCode.BILLET_MONTHLY_SEARCH_SUCCESS, "ok", billetAmountForCurrent12Months);
    }

    @GetMapping("/quarterly")
    public SuccessResult<Statistics> billetQuarterlyStatistics(){
        Statistics billetAmountForCurrent4Quarter = statisticsService.findAvgAndDeviationDataForCurrent4Quarter(statisticsType);
        return new SuccessResult<>(DomainConditionCode.BILLET_QUARTERLY_SEARCH_SUCCESS, "ok", billetAmountForCurrent4Quarter);
    }

    @GetMapping("/yearly")
    public SuccessResult<Statistics> billetYearlyStatistics(){
        Statistics billetAmountForCurrent4Years = statisticsService.findAvgAndDeviationDataForCurrent4Years(statisticsType);
        return new SuccessResult<>(DomainConditionCode.BILLET_YEARLY_SEARCH_SUCCESS, "ok", billetAmountForCurrent4Years);
    }



}
