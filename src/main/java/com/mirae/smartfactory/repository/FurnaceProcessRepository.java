package com.mirae.smartfactory.repository;

import com.mirae.smartfactory.domain.model.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.model.process.furnace.Ingredient;
import com.mirae.smartfactory.domain.model.resource.Additive;
import com.mirae.smartfactory.domain.model.resource.Material;
import com.mirae.smartfactory.domain.repository.IFurnaceProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FurnaceProcessRepository implements IFurnaceProcessRepository {

    private final EntityManager em;

    public void save(FurnaceProcess furnaceProcess) {
        em.persist(furnaceProcess);
    }

    public void update(FurnaceProcess furnaceProcess) {
        em.merge(furnaceProcess);
    }

    public void delete(FurnaceProcess furnaceProcess) {
        em.remove(furnaceProcess);
    }

    public void deleteIngredient(Ingredient ingredient) {
        em.remove(ingredient);
    }

    public Optional<FurnaceProcess> findById(Long id) {
        return Optional.ofNullable(em.find(FurnaceProcess.class, id));
    }

    public Optional<FurnaceProcess> findByProcessId(Long processId) {
        return Optional.ofNullable(
                em.createQuery(
                                "select f from FurnaceProcess f" +
                                        " where f.process.processId = :processId", FurnaceProcess.class)
                        .setParameter("processId", processId)
                        .getResultStream().findFirst().orElse(null)
        );
    }

    public List<FurnaceProcess> findListByDate(LocalDate date) {
        return em.createQuery(
                        "select f from FurnaceProcess f" +
                                " join fetch f.process p" +
                                " where p.date = :date", FurnaceProcess.class
                ).setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Ingredient> findIngredientList(Long furnaceProcessId) {
        return em.createQuery(
                "select i from Ingredient i" +
                        " where i.furnaceProcess.furnaceProcessId = :furnaceProcessId", Ingredient.class)
                .setParameter("furnaceProcessId", furnaceProcessId)
                .getResultList();
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        em.persist(ingredient);
    }


}
