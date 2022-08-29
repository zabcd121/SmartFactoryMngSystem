package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FurnaceProcessRepository {

    private final EntityManager em;

    public void save(FurnaceProcess furnaceProcess) {
        em.persist(furnaceProcess);
    }

    public List<FurnaceProcess> findListByDate(LocalDate date) {
        return em.createQuery(
                        "select f from FurnaceProcess f" +
                                " join fetch f.process p" +
//                                " join fetch f.ingredients ig" +
                                " where p.date = :date", FurnaceProcess.class
                ).setParameter("date", date)
                .getResultList();
    }

    public Integer findTotalAshesAmountByDate(LocalDate date) {
        return em.createQuery(
                        "select sum(f.ashesAmount) from FurnaceProcess f" +
                                " join fetch f.process p" +
                                " where p.date = :date" , Integer.class)
                .setParameter("date", date)
                .getSingleResult();
    }

    public Integer findTotalAshesAmountByStartDateAndFinishDate(LocalDate startDate, LocalDate finishDate) {
        return em.createQuery(
                "select sum(f.ashesAmount) from FurnaceProcess f" +
                        " join fetch f.process p" +
                        " where p.date between :startDate and :finishDate" +
                        " order by p.date", Integer.class)
                .setParameter("startDate", startDate)
                .setParameter("finishDate", finishDate)
                .getSingleResult();
    }
}
