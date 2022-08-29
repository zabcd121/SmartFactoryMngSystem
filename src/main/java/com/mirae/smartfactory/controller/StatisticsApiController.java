package com.mirae.smartfactory.controller;

import com.mirae.smartfactory.domain.statistics.Statistics;
import com.mirae.smartfactory.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsApiController {

    private final StatisticsService statisticsService;

    @GetMapping("/ashes/daily")
    public Statistics ashesDayStatistics(){
        return statisticsService.findAshesAmountForSevenDays();
    }

}
