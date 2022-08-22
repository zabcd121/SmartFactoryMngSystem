package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.process.casting.Casting;
import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CastingRepository {

    private final EntityManager em;

    public void save(Casting casting) {
        em.persist(casting);
    }

    public List<Casting> findListByDate(LocalDate date) {
        return em.createQuery(
                        "select c from Casting c" +
                                " join fetch c.process p" +
                                " join fetch c. castingPreparation cp" +
                                " join fetch c. castingData cd" +
                                " join fetch c. castingTemperature ct" +
                                " join fetch c. billet b" +
                                " where p.date = :date", Casting.class
                ).setParameter("date", date)
                .getResultList();
    }
}
