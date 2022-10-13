package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.domain.model.process.furnace.Ingredient;
import com.mirae.smartfactory.domain.model.resource.Additive;
import com.mirae.smartfactory.domain.model.resource.Material;
import com.mirae.smartfactory.domain.repository.IProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProcessRepository implements IProcessRepository {

    private final EntityManager em;

    @Override
    public void save(Process process) {
        em.persist(process);
    }
    @Override
    public Process findOne(Long id) {
        return em.find(Process.class, id);
    }

    @Override
    public Process findByDateAndDailyProcessId(LocalDate date, Integer dailyProcessId) {
        return em.createQuery(
                "select p from Process p" +
                        " where p.date = :date and p.dailyProcessId = :dailyProcessId", Process.class)
                .setParameter("date", date)
                .setParameter("dailyProcessId", dailyProcessId)
                .getSingleResult();
    }

    @Override
    public List<Material> findMaterialList(Long processId) {
        return em.createQuery(
                        "select m from Material m" +
                                " where m.process.processId = :processId", Material.class)
                .setParameter("processId", processId)
                .getResultList();
    }

    @Override
    public List<Additive> findAdditiveList(Long processId) {
        return em.createQuery(
                        "select a from Additive a" +
                                " where a.process.processId = :processId", Additive.class)
                .setParameter("processId", processId)
                .getResultList();
    }

    @Override
    public void saveMaterial(Material material) {
        em.persist(material);
    }

    @Override
    public void saveAdditive(Additive additive) {
        em.persist(additive);
    }

    @Override
    public void deleteMaterial(Material material) {
        em.remove(material);
    }

    @Override
    public void deleteAdditive(Additive additive) {
        em.remove(additive);
    }
}
