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

//    public List<Process> findListByDate(LocalDate date) {
//        return em.createQuery(
//                        "select p from Process p" +
//                                " join fetch p.furnaceProcess f" +
//                                " join fetch p.member m" +
//                                " where p.date = :date", Process.class
//                ).setParameter("date", date)
//                .getResultList();
//    }



}

//    public List<ProcessDto> findAllFurnaceProcess(LocalDate date) {
//        List<ProcessDto> result = findFurnaceProcessesByDate(date);
//
//        List<Long> processIds = toProcessIds(result);
//
//        Map<Long, List<MaterialDto>> materialMap = findMaterialMap(processIds);
//        result.forEach(p -> p.setMaterials(materialMap.get(p.getProcessId())));
//
//        Map<Long, List<AdditiveDto>> additiveMap = findAdditiveMap(processIds);
//        result.forEach(p -> p.setAdditives(additiveMap.get(p.getProcessId())));
//
//        return result;
//    }
//
//    private List<ProcessDto> findFurnaceProcessesByDate(LocalDate date) {
//        return em.createQuery(
//                        "select new com.mirae.smartfactory.dto.furnace.ProcessDto(p.processId, p.date, p.dailyProcessId, p.furnaceNumber, p.alloyCode, p.size, p.member)" +
//                                " from Process p" +
//                                " join p.member m" +
//                                " where p.date = :date", ProcessDto.class
//                ).setParameter("date", date)
//                .getResultList();
//    }
//
//    private List<FurnaceProcessDto> findFurnaceProcessDtos() {
//        return em.createQuery(
//                "select new com.mirae.smartfactory.dto.furnace.FurnaceProcessDto()" +
//                        " from FurnaceProcess fp" +
//                        " join IngredientAnalysis ia"
//        )
//    }
//
//    private List<Long> toProcessIds(List<ProcessDto> result) {
//        return result.stream()
//                .map(p -> p.getProcessId())
//                .collect(Collectors.toList());
//    }
//
//    private Map<Long, List<MaterialDto>> findMaterialMap(List<Long> processIds) {
//        List<MaterialDto> materials = em.createQuery(
//                        "select new com.mirae.smartfactory.dto.MaterialDto(m.process.processId, m.materialType, m.materialName, m.weight)" +
//                                " from Material m" +
//                                " where m.process.processId in :processIds", MaterialDto.class)
//                .setParameter("processIds", processIds)
//                .getResultList();
//
//        return materials.stream()
//                .collect(Collectors.groupingBy(MaterialDto::getProcessId));
//    }
//
//    private Map<Long, List<AdditiveDto>> findAdditiveMap(List<Long> processIds) {
//        List<AdditiveDto> additives = em.createQuery(
//                        "select new com.mirae.smartfactory.dto.AdditiveDto(a.process.processId, a.additiveName, a.additiveWeight)" +
//                                " from Additive a" +
//                                " where a.process.processId in :processIds", AdditiveDto.class)
//                .setParameter("processIds", processIds)
//                .getResultList();
//
//        return additives.stream()
//                .collect(Collectors.groupingBy(AdditiveDto::getProcessId));
//    }
