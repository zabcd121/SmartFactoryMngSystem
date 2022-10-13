package com.mirae.smartfactory.controller.statistics;

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
@RequestMapping("/statistics/billet")
@RequiredArgsConstructor
public class BilletStatisticsApiController {
    private final StatisticsService statisticsService;
    private static final StatisticsType statisticsType = StatisticsType.BILLET;

    @GetMapping("/daily")
    public SuccessResult<Statistics> billetDailyStatistics(){

        Statistics billetAmountForCurrent7Days = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(statisticsType);
        return new SuccessResult<>(BILLET_DAILY_SEARCH_SUCCESS.getCode(), BILLET_DAILY_SEARCH_SUCCESS.getMessage(), billetAmountForCurrent7Days);
    }

    @GetMapping("/monthly")
    public SuccessResult<Statistics> billetMonthlyStatistics(){
        Statistics billetAmountForCurrent12Months = statisticsService.findMonthlyAvgAndDeviationDataForCurrent12Months(statisticsType);
        return new SuccessResult<>(BILLET_MONTHLY_SEARCH_SUCCESS.getCode(), BILLET_MONTHLY_SEARCH_SUCCESS.getMessage(), billetAmountForCurrent12Months);
    }

    @GetMapping("/quarterly")
    public SuccessResult<Statistics> billetQuarterlyStatistics(){
        Statistics billetAmountForCurrent4Quarter = statisticsService.findAvgAndDeviationDataForCurrent4Quarter(statisticsType);
        return new SuccessResult<>(BILLET_QUARTERLY_SEARCH_SUCCESS.getCode(), BILLET_QUARTERLY_SEARCH_SUCCESS.getMessage(), billetAmountForCurrent4Quarter);
    }

    @GetMapping("/yearly")
    public SuccessResult<Statistics> billetYearlyStatistics(){
        Statistics billetAmountForCurrent4Years = statisticsService.findAvgAndDeviationDataForCurrent4Years(statisticsType);
        return new SuccessResult<>(BILLET_YEARLY_SEARCH_SUCCESS.getCode(), BILLET_YEARLY_SEARCH_SUCCESS.getMessage(), billetAmountForCurrent4Years);
    }


}
