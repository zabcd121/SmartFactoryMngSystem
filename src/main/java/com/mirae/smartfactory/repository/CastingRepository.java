package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.model.process.casting.Casting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CastingRepository {

    private final EntityManager em;

    public void save(Casting casting) {
        em.persist(casting);
    }

    public void update(Casting casting) {
        em.merge(casting);
    }

    public void delete(Casting casting) {
        em.remove(casting);
    }

    public Optional<Casting> findById(Long id) {
        return Optional.ofNullable(em.find(Casting.class, id));
    }

    public Optional<Casting> findByProcessId(Long processId) {
        return Optional.ofNullable(
                em.createQuery(
                        "select c from Casting c" +
                                " where c.process.processId = :processId", Casting.class)
                    .setParameter("processId", processId)
                    .getResultStream().findFirst().orElse(null)
                );
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
