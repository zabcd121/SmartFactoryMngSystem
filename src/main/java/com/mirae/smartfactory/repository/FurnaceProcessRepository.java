package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                                " join fetch f.ingredientAnalysis ia" +
                                " where p.date = :date", FurnaceProcess.class
                ).setParameter("date", date).
                getResultList();
    }

}
