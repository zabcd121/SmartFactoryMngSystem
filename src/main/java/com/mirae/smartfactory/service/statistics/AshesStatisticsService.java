package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.statistics.Statistics;
import com.mirae.smartfactory.dto.statistics.DailyStatisticsDto;
import com.mirae.smartfactory.repository.statistics.FurnaceProcessQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AshesStatisticsService {

    private final FurnaceProcessQueryRepository furnaceProcessQueryRepository;

    public DailyStatisticsDto findAshesAmountForCurrent7Days(){
        LocalDate startDate = LocalDate.now().minusDays(6);
        LocalDate finishDate = LocalDate.now();

        Statistics dailyAshesStatistics = Statistics.createAshesStatistics();

        Double avgAshesFor7Days = furnaceProcessQueryRepository.findAvgAshesAmountByStartDateAndFinishDate(startDate, finishDate);
        Double avgAshesAmountByDate = 0.0D;
        for(int i=0; i<7; i++) {
            LocalDate targetDate = startDate.plusDays(i);
            avgAshesAmountByDate = furnaceProcessQueryRepository.findAvgAshesAmountByDate(targetDate);
            log.info("avgAshesAmountByDate = {}", avgAshesAmountByDate);

//            if(avgAshesFor7Days == null) {
//                avgAshesFor7Days = 0.0D;
//            }else if(avgAshesAmountByDate == null) {
//                avgAshesAmountByDate = 0.0D;
//            }
            dailyAshesStatistics.addDailyDatum(targetDate, avgAshesAmountByDate, avgAshesFor7Days);
        }

        return new DailyStatisticsDto(dailyAshesStatistics);
    }

    public Statistics findAshesAmountForCurrent12Months() {
        YearMonth startYearMonth = YearMonth.now().minusMonths(11);
        YearMonth finishYearMonth = YearMonth.now();

        Statistics monthlyAshesStatistics = Statistics.createAshesStatistics();

        Double avgAshesAmountFor12Month = furnaceProcessQueryRepository.findAvgAshesAmountByStartDateAndFinishDate(startYearMonth.atDay(1), finishYearMonth.atDay(1));
        Double avgAshesAmountByYearMonth = 0D;
        for(int i=0; i<12; i++) {
            YearMonth targetYearMonth = startYearMonth.plusMonths(i);
            avgAshesAmountByYearMonth = furnaceProcessQueryRepository.findAvgAshesAmountByYearMonth(targetYearMonth);

            monthlyAshesStatistics.addMonthlyDatum(targetYearMonth, avgAshesAmountByYearMonth, avgAshesAmountFor12Month);
        }

        return monthlyAshesStatistics;
    }

    public Statistics findAshesAmountForCurrent4Quarter() {
        YearMonth startYearMonth = getYearMonthOfStartQuarter();
        YearMonth finishYearMonth = YearMonth.now();

        Statistics quarterlyAshesStatistics = Statistics.createAshesStatistics();

        Double avgAshesAmountFor4Quarter = furnaceProcessQueryRepository.findAvgAshesAmountByStartDateAndFinishDate(startYearMonth.atDay(1), finishYearMonth.atDay(finishYearMonth.lengthOfMonth()));
        Double avgAshesAmountByQuarter = 0D;
        for(int i=0; i<4; i++) {
            YearMonth targetYearMonth = startYearMonth.plusMonths(i);
            avgAshesAmountByQuarter = furnaceProcessQueryRepository.findAvgAshesAmountByStartDateAndFinishDate(targetYearMonth.atDay(1),
                                                                                                                targetYearMonth.plusMonths(2).atDay(1));
            quarterlyAshesStatistics.addQuarterlyDatum(targetYearMonth, avgAshesAmountByQuarter, avgAshesAmountFor4Quarter);
        }

        return quarterlyAshesStatistics;
    }

    public Statistics findAshesAmountForCurrent4Years() {
        YearMonth startYearMonth = YearMonth.of(YearMonth.now().minusYears(3).getYear(), Month.JANUARY);
        YearMonth finishYearMonth = YearMonth.now();

        Statistics yearlyAshesStatistics = Statistics.createAshesStatistics();

        Double avgAshesAmountFor4Years = furnaceProcessQueryRepository.findAvgAshesAmountByStartDateAndFinishDate(startYearMonth.atDay(1), finishYearMonth.atDay(finishYearMonth.lengthOfMonth()));
        Double avgAshesAmountByYear = 0D;
        for(int i=0; i<4; i++) {
            YearMonth targetYearMonth = startYearMonth.plusYears(i);
            avgAshesAmountByYear = furnaceProcessQueryRepository.findAvgAshesAmountByStartDateAndFinishDate(targetYearMonth.atDay(1),
                                                                                                            targetYearMonth.plusMonths(11).atDay(Month.DECEMBER.maxLength()));
            yearlyAshesStatistics.addYearlyDatum(targetYearMonth, avgAshesAmountByYear, avgAshesAmountFor4Years);
        }

        return yearlyAshesStatistics;
    }

    private YearMonth getYearMonthOfStartQuarter() {
        YearMonth startYearMonth = null;
        int currentMonth = YearMonth.now().getMonthValue();

        if(currentMonth == 1 || currentMonth == 4 || currentMonth == 7 || currentMonth == 10) {
            startYearMonth = YearMonth.now().minusMonths(9);
        }else if(currentMonth == 2 || currentMonth == 5 || currentMonth == 8 || currentMonth == 11) {
            startYearMonth = YearMonth.now().minusMonths(10);
        } else if(currentMonth == 3 || currentMonth == 6 || currentMonth == 9 || currentMonth == 12) {
            startYearMonth = YearMonth.now().minusMonths(11);
        }

        return startYearMonth;
    }
}
