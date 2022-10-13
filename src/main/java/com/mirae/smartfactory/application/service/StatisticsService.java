package com.mirae.smartfactory.application.service;

import com.mirae.smartfactory.domain.model.statistics.Statistics;
import com.mirae.smartfactory.domain.model.statistics.StatisticsType;
import com.mirae.smartfactory.repository.statistics.StatisticsRepository;
import com.mirae.smartfactory.repository.statistics.StatisticsRepositoryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class StatisticsService {

    private StatisticsRepositoryFactory statisticsRepoFactory;

    public StatisticsService(StatisticsRepositoryFactory statisticsRepoFactory) {
        this.statisticsRepoFactory = statisticsRepoFactory;
    }

    public Statistics findDailyAvgAndDeviationDataForCurrent7Days(StatisticsType statisticsType){
        StatisticsRepository repository = statisticsRepoFactory.getRepository(statisticsType);

        LocalDate startDate = LocalDate.now().minusDays(6);
        LocalDate finishDate = LocalDate.now();

        Statistics dailyStatistics = Statistics.createStatistics();

        Double avgFor7Days = repository.findAvgByStartDateAndFinishDate(startDate, finishDate).orElseGet(() -> 0.0D);
        log.info("avgFor7Days {}", avgFor7Days);
        Double avgAmountByDate = 0.0D;
        for(int i=0; i<7; i++) {
            LocalDate targetDate = startDate.plusDays(i);
            avgAmountByDate = repository.findAvgByDate(targetDate).orElseGet(() -> 0.0D);
            dailyStatistics.addDailyDatum(targetDate, avgAmountByDate, avgFor7Days);
            log.info("avgAmountByDate {}", avgAmountByDate);
        }

        return dailyStatistics;
    }

    public Statistics findMonthlyAvgAndDeviationDataForCurrent12Months(StatisticsType statisticsType) {
        StatisticsRepository repository = statisticsRepoFactory.getRepository(statisticsType);

        YearMonth startYearMonth = YearMonth.now().minusMonths(11);
        YearMonth finishYearMonth = YearMonth.now();

        Statistics monthlyStatistics = Statistics.createStatistics();

        Double avgAmountFor12Month = repository.findAvgByStartDateAndFinishDate(startYearMonth.atDay(1), finishYearMonth.atDay(LocalDate.now().getDayOfMonth())).orElseGet(() -> 0.0D);
        Double avgAmountByYearMonth = 0.0D;
        for(int i=0; i<12; i++) {
            YearMonth targetYearMonth = startYearMonth.plusMonths(i);
            Optional<Double> optionalAvgAmountByYearMonth = repository.findAvgByYearMonth(targetYearMonth);
            avgAmountByYearMonth = optionalAvgAmountByYearMonth.orElseGet(() -> 0.0D);
            monthlyStatistics.addMonthlyDatum(targetYearMonth, avgAmountByYearMonth, avgAmountFor12Month);
        }

        return monthlyStatistics;
    }

    public Statistics findAvgAndDeviationDataForCurrent4Quarter(StatisticsType statisticsType) {
        StatisticsRepository repository = statisticsRepoFactory.getRepository(statisticsType);

        YearMonth startYearMonth = getYearMonthOfStartQuarter();
        YearMonth finishYearMonth = YearMonth.now();

        Statistics quarterlyStatistics = Statistics.createStatistics();

        Double avgAmountFor4Quarter = repository.findAvgByStartDateAndFinishDate(startYearMonth.atDay(1), finishYearMonth.atDay(LocalDate.now().getDayOfMonth())).orElseGet(() -> 0.0D);;
        Double avgAmountByQuarter = 0.0D;
        for(int i=0; i<4; i++) {
            LocalDate startDateOfTargetQuarter = startYearMonth.plusMonths(3*i).atDay(1);
            LocalDate finishDateOfTargetQuarter = startYearMonth.plusMonths(3*i + 2).atEndOfMonth();

            avgAmountByQuarter = repository.findAvgByStartDateAndFinishDate(startDateOfTargetQuarter, finishDateOfTargetQuarter).orElseGet(() -> 0.0D);;

            quarterlyStatistics.addQuarterlyDatum(startDateOfTargetQuarter, avgAmountByQuarter, avgAmountFor4Quarter);
        }
        return quarterlyStatistics;
    }

    public Statistics findAvgAndDeviationDataForCurrent4Years(StatisticsType statisticsType) {
        StatisticsRepository repository = statisticsRepoFactory.getRepository(statisticsType);

        YearMonth startYearMonth = YearMonth.of(YearMonth.now().minusYears(3).getYear(), Month.JANUARY);
        YearMonth finishYearMonth = YearMonth.now();

        Statistics yearlyStatistics = Statistics.createStatistics();

        Double avgAmountFor4Years = repository.findAvgByStartDateAndFinishDate(startYearMonth.atDay(1), finishYearMonth.atDay(finishYearMonth.lengthOfMonth())).orElseGet(() -> 0.0D);
        Double avgAmountByYear = 0.0D;
        for(int i=0; i<4; i++) {
            YearMonth targetYearMonth = startYearMonth.plusYears(i);
            avgAmountByYear = repository.findAvgByStartDateAndFinishDate(targetYearMonth.atDay(1),
                    targetYearMonth.plusMonths(11).atDay(Month.DECEMBER.maxLength())).orElseGet(() -> 0.0D);
            yearlyStatistics.addYearlyDatum(targetYearMonth, avgAmountByYear, avgAmountFor4Years);
        }

        return yearlyStatistics;
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
