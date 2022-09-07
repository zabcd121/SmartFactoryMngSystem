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
@RequestMapping("/statistics/defective")
@RequiredArgsConstructor
public class DefectiveStatisticsApiController {

    private final StatisticsService statisticsService;
    private static final StatisticsType statisticsType = StatisticsType.DEFECTIVE;

    @GetMapping("/daily")
    public SuccessResult<Statistics> defectiveDailyStatistics(){

        Statistics defectiveAmountForCurrent7Days = statisticsService.findDailyAvgAndDeviationDataForCurrent7Days(statisticsType);
        return new SuccessResult<>(DomainConditionCode.DEFECTIVE_DAILY_SEARCH_SUCCESS, "ok", defectiveAmountForCurrent7Days);
    }

    @GetMapping("/monthly")
    public SuccessResult<Statistics> defectiveMonthlyStatistics(){
        Statistics defectiveAmountForCurrent12Months = statisticsService.findMonthlyAvgAndDeviationDataForCurrent12Months(statisticsType);
        return new SuccessResult<>(DomainConditionCode.DEFECTIVE_MONTHLY_SEARCH_SUCCESS, "ok", defectiveAmountForCurrent12Months);
    }

    @GetMapping("/quarterly")
    public SuccessResult<Statistics> defectiveQuarterlyStatistics(){
        Statistics defectiveAmountForCurrent4Quarter = statisticsService.findAvgAndDeviationDataForCurrent4Quarter(statisticsType);
        return new SuccessResult<>(DomainConditionCode.DEFECTIVE_QUARTERLY_SEARCH_SUCCESS, "ok", defectiveAmountForCurrent4Quarter);
    }

    @GetMapping("/yearly")
    public SuccessResult<Statistics> defectiveYearlyStatistics(){
        Statistics defectiveAmountForCurrent4Years = statisticsService.findAvgAndDeviationDataForCurrent4Years(statisticsType);
        return new SuccessResult<>(DomainConditionCode.DEFECTIVE_YEARLY_SEARCH_SUCCESS, "ok", defectiveAmountForCurrent4Years);
    }
}
