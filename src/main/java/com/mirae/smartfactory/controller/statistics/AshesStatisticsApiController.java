package com.mirae.smartfactory.controller.statistics;


import com.mirae.smartfactory.consts.ConditionCode;
import com.mirae.smartfactory.domain.model.statistics.Statistics;
import com.mirae.smartfactory.domain.model.statistics.StatisticsType;
import com.mirae.smartfactory.dto.result.SuccessResult;
import com.mirae.smartfactory.application.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mirae.smartfactory.consts.ConditionCode.*;

@RestController
@RequestMapping("/statistics/ashes")
@RequiredArgsConstructor
public class AshesStatisticsApiController {
    private final StatisticsService statisticsService;
    private static final StatisticsType statisticsType = StatisticsType.ASHES;

    @GetMapping("/daily")
    public SuccessResult<Statistics> ashesDailyStatistics(){

        Statistics ashesAmountForCurrent7Days = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(statisticsType);
        return new SuccessResult<>(ASHES_DAILY_SEARCH_SUCCESS.getCode(), ASHES_DAILY_SEARCH_SUCCESS.getMessage(), ashesAmountForCurrent7Days);
    }

    @GetMapping("/monthly")
    public SuccessResult<Statistics> ashesMonthlyStatistics(){
        Statistics ashesAmountForCurrent12Months = statisticsService.findMonthlyAvgAndDeviationDataForCurrent12Months(statisticsType);
        return new SuccessResult<>(ASHES_MONTHLY_SEARCH_SUCCESS.getCode(), ASHES_MONTHLY_SEARCH_SUCCESS.getMessage(), ashesAmountForCurrent12Months);
    }

    @GetMapping("/quarterly")
    public SuccessResult<Statistics> ashesQuarterlyStatistics(){
        Statistics ashesAmountForCurrent4Quarter = statisticsService.findAvgAndDeviationDataForCurrent4Quarter(statisticsType);
        return new SuccessResult<>(ASHES_QUARTERLY_SEARCH_SUCCESS.getCode(), ASHES_QUARTERLY_SEARCH_SUCCESS.getMessage(), ashesAmountForCurrent4Quarter);
    }

    @GetMapping("/yearly")
    public SuccessResult<Statistics> ashesYearlyStatistics(){
        Statistics ashesAmountForCurrent4Years = statisticsService.findAvgAndDeviationDataForCurrent4Years(statisticsType);
        return new SuccessResult<>(ASHES_YEARLY_SEARCH_SUCCESS.getCode(), ASHES_YEARLY_SEARCH_SUCCESS.getMessage(), ashesAmountForCurrent4Years);
    }

}
