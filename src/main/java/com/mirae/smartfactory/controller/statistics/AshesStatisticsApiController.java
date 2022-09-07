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
@RequestMapping("/statistics/ashes")
@RequiredArgsConstructor
public class AshesStatisticsApiController {
    private final StatisticsService statisticsService;
    private static final StatisticsType statisticsType = StatisticsType.ASHES;

    @GetMapping("/daily")
    public SuccessResult<Statistics> ashesDailyStatistics(){

        Statistics ashesAmountForCurrent7Days = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(statisticsType);
        return new SuccessResult<>(DomainConditionCode.ASHES_DAILY_SEARCH_SUCCESS, "ok", ashesAmountForCurrent7Days);
    }

    @GetMapping("/monthly")
    public SuccessResult<Statistics> ashesMonthlyStatistics(){
        Statistics ashesAmountForCurrent12Months = statisticsService.findMonthlyAvgAndDeviationDataForCurrent12Months(statisticsType);
        return new SuccessResult<>(DomainConditionCode.ASHES_MONTHLY_SEARCH_SUCCESS, "ok", ashesAmountForCurrent12Months);
    }

    @GetMapping("/quarterly")
    public SuccessResult<Statistics> ashesQuarterlyStatistics(){
        Statistics ashesAmountForCurrent4Quarter = statisticsService.findAvgAndDeviationDataForCurrent4Quarter(statisticsType);
        return new SuccessResult<>(DomainConditionCode.ASHES_QUARTERLY_SEARCH_SUCCESS, "ok", ashesAmountForCurrent4Quarter);
    }

    @GetMapping("/yearly")
    public SuccessResult<Statistics> ashesYearlyStatistics(){
        Statistics ashesAmountForCurrent4Years = statisticsService.findAvgAndDeviationDataForCurrent4Years(statisticsType);
        return new SuccessResult<>(DomainConditionCode.ASHES_YEARLY_SEARCH_SUCCESS, "ok", ashesAmountForCurrent4Years);
    }

}
