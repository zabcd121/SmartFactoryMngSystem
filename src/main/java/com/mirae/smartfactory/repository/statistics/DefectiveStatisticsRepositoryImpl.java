package com.mirae.smartfactory.repository.statistics;

import com.mirae.smartfactory.domain.model.statistics.StatisticsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefectiveStatisticsRepositoryImpl implements StatisticsRepository {
    private final EntityManager em;

    @Override
    public Optional<Double> findAvgByDate(LocalDate date) {
        return Optional.ofNullable(em.createQuery(
                        "select avg(b.errorQuantity) from Casting c" +
                                " join c.process p" +
                                " join c.billet b" +
                                " where p.date = :date", Double.class)
                .setParameter("date", date)
                .getSingleResult());
    }

    @Override
    public Optional<Double> findAvgByYearMonth(YearMonth yearMonth) {
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        return Optional.ofNullable(em.createQuery(
                        "select avg(b.errorQuantity) from Casting c" +
                                " join c.process p" +
                                " join c.billet b" +
                                " where year(p.date) = :year and" +
                                " month(p.date) = :month", Double.class)
                .setParameter("year", year)
                .setParameter("month", month)
                .getSingleResult());
    }

    @Override
    public Optional<Double> findAvgByStartDateAndFinishDate(LocalDate startDate, LocalDate finishDate) {
        LocalDate nextDayofFinishDay = finishDate.plusDays(1); //between a and b가 b는 2022/09/05이면 2022/09/05 00:00:00 까지만 해서 그날은 포함이 안되므로 1일 플러스해줌
        return Optional.ofNullable(em.createQuery(
                        "select avg(b.errorQuantity) from Casting c" +
                                " join c.process p" +
                                " join c.billet b" +
                                " where p.date between :startDate and :nextDayofFinishDay", Double.class)
                .setParameter("startDate", startDate)
                .setParameter("nextDayofFinishDay", nextDayofFinishDay)
                .getSingleResult());
    }

    @Override
    public StatisticsType getType() {
        return StatisticsType.DEFECTIVE;
    }


}
