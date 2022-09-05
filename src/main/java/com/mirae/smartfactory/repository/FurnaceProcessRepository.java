package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FurnaceProcessRepository {

    private final EntityManager em;

    public void save(FurnaceProcess furnaceProcess) {
        em.persist(furnaceProcess);
    }

    public void update(FurnaceProcess furnaceProcess) {
        em.merge(furnaceProcess);
    }

    public List<FurnaceProcess> findListByDate(LocalDate date) {
        return em.createQuery(
                        "select f from FurnaceProcess f" +
                                " join fetch f.process p" +
                                " where p.date = :date", FurnaceProcess.class
                ).setParameter("date", date)
                .getResultList();
    }




}
