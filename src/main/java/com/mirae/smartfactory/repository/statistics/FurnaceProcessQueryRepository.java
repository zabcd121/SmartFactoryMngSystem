package com.mirae.smartfactory.repository.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.YearMonth;

@Repository
@RequiredArgsConstructor
public class FurnaceProcessQueryRepository {

    private final EntityManager em;

    public Double findAvgAshesAmountByDate(LocalDate date) {
        return em.createQuery(
                        "select avg(f.ashesAmount) from FurnaceProcess f" +
                                " join f.process p" +
                                " where p.date = :date", Double.class)
                .setParameter("date", date)
                .getSingleResult();
    }

    public Double findAvgAshesAmountByYearMonth(YearMonth yearMonth) {
        return em.createQuery(
                        "select avg(f.ashesAmount) from FurnaceProcess f" +
                                " join f.process p" +
                                " where year(p.date) = year(:yearMonth) and" +
                                " month(p.date) = month(:yearMonth)", Double.class)
                .setParameter("yearMonth", yearMonth)
                .getSingleResult();
    }

//    public Float findQuarterAvgAshesAmountByStartDate(LocalDate startDate) {
//        LocalDate finishDate = startDate.plusMonths(2);
//        return em.createQuery(
//                        "select avg(f.ashesAmount) from FurnaceProcess f" +
//                                " join fetch f.process p" +
//                                " where p.date between :startDate and :finishDate", Float.class)
//                .setParameter("startDate", startDate)
//                .setParameter("finishDate", finishDate)
//                .getSingleResult();
//    }

    public Double findAvgAshesAmountByStartDateAndFinishDate(LocalDate startDate, LocalDate finishDate) {
        return em.createQuery(
                        "select avg(f.ashesAmount) from FurnaceProcess f" +
                                " join f.process p" +
                                " where p.date between :startDate and :finishDate", Double.class)
                .setParameter("startDate", startDate)
                .setParameter("finishDate", finishDate)
                .getSingleResult();
    }

}
