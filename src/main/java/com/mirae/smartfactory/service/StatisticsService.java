package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.statistics.Statistics;
import com.mirae.smartfactory.repository.FurnaceProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatisticsService {

    private final FurnaceProcessRepository furnaceProcessRepository;

    public Statistics findAshesAmountForSevenDays(){
        LocalDate startDate = LocalDate.now().minusDays(6);
        LocalDate finishDate = LocalDate.now();

        Statistics dailyAshesStatistics = Statistics.createDailyAshesStatistics();

        Integer totalAshesAmountForSevenDays = furnaceProcessRepository.findTotalAshesAmountByStartDateAndFinishDate(startDate, finishDate);

        for(int i=0; i<7; i++) {
            LocalDate targetDate = startDate;
            Integer totalAshesAmountByDate = furnaceProcessRepository.findTotalAshesAmountByDate(targetDate.plusDays(i));
            dailyAshesStatistics.addDailyDatum(targetDate, totalAshesAmountByDate, totalAshesAmountForSevenDays);
        }

        return dailyAshesStatistics;
    }
}
