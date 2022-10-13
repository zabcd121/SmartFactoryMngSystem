package com.mirae.smartfactory.repository.statistics;

import com.mirae.smartfactory.domain.model.statistics.StatisticsType;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

public interface StatisticsRepository {

    public Optional<Double> findAvgByDate(LocalDate date);

    public Optional<Double> findAvgByYearMonth(YearMonth yearMonth);

    public Optional<Double> findAvgByStartDateAndFinishDate(LocalDate startDate, LocalDate finishDate);

    public StatisticsType getType();
}
