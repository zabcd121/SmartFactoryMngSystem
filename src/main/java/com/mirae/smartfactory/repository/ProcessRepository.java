package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.process.Process;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProcessRepository {

    private final EntityManager em;

    public void save(Process process) {
        em.persist(process);
    }

    public Process findOne(Long id) {
        return em.find(Process.class, id);
    }

    public Process findByDateAndDailyProcessId(LocalDate date, Integer dailyProcessId) {
        return em.createQuery(
                "select p from Process p" +
                        " where p.date = :date and p.dailyProcessId = :dailyProcessId", Process.class)
                .setParameter("date", date)
                .setParameter("dailyProcessId", dailyProcessId)
                .getSingleResult();
    }
}
