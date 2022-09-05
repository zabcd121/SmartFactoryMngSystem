package com.mirae.smartfactory.controller;

import com.mirae.smartfactory.domain.statistics.Statistics;
import com.mirae.smartfactory.dto.statistics.DailyStatisticsListDto;
import com.mirae.smartfactory.dto.statistics.StatisticsListDto;
import com.mirae.smartfactory.service.statistics.AshesStatisticsService;
import com.mirae.smartfactory.service.statistics.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsApiController {

    private final AshesStatisticsService ashesStatisticsService;
    private final StatisticsService statisticsService;

    @GetMapping("/daily")
    public DailyStatisticsListDto dailyStatistics() {
        return statisticsService.findTotalDomainDailyStatistics();
    }

    @GetMapping("/ashes/daily")
    public Statistics ashesDailyStatistics(){
        return ashesStatisticsService.findAshesAmountForCurrent7Days();
    }

    @GetMapping("/ashes/monthly")
    public Statistics ashesMonthlyStatistics(){
        return ashesStatisticsService.findAshesAmountForCurrent12Months();
    }

    @GetMapping("/ashes/quarterly")
    public Statistics ashesQuarterlyStatistics(){
        return ashesStatisticsService.findAshesAmountForCurrent4Quarter();
    }

    @GetMapping("/ashes/yearly")
    public Statistics ashesYearlyStatistics(){
        return ashesStatisticsService.findAshesAmountForCurrent4Years();
    }

    @GetMapping("/billet/daily")
    public Statistics billetDailyStatistics(){
        return ashesStatisticsService.findAshesAmountForCurrent7Days();
    }

    @GetMapping("/billet/monthly")
    public Statistics billetMonthlyStatistics(){
        return ashesStatisticsService.findAshesAmountForCurrent12Months();
    }

    @GetMapping("/billet/quarterly")
    public Statistics billetQuarterlyStatistics(){
        return ashesStatisticsService.findAshesAmountForCurrent4Quarter();
    }

    @GetMapping("/billet/yearly")
    public Statistics billetYearlyStatistics(){
        return ashesStatisticsService.findAshesAmountForCurrent4Years();
    }



}
