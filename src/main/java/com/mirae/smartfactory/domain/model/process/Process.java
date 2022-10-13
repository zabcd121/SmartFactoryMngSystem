package com.mirae.smartfactory.domain.model.process;

import com.mirae.smartfactory.domain.model.resource.Additive;
import com.mirae.smartfactory.domain.model.resource.Material;
import com.mirae.smartfactory.domain.model.resource.Member;
import com.mirae.smartfactory.domain.service.FurnaceProcessUpdateService;
import com.mirae.smartfactory.dto.process.ProcessDto;
import com.mirae.smartfactory.dto.process.casting.ProcessSimpleDto;
import com.mirae.smartfactory.dto.resource.AdditiveDto;
import com.mirae.smartfactory.dto.resource.MaterialDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Process {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long processId;

    private LocalDate date;

    private Integer dailyProcessId;

    private Integer furnaceNumber;

    private Integer alloyCode;

    private Integer size;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<Material> materials = new ArrayList<>();

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<Additive> additives = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public Process(LocalDate date, Integer dailyProcessId, Integer furnaceNumber, Integer alloyCode, Integer size, Member member) {
        this.date = date;
        this.dailyProcessId = dailyProcessId;
        this.furnaceNumber = furnaceNumber;
        this.alloyCode = alloyCode;
        this.size = size;
        this.member = member;
    }

    //==양방향 연관관계 메서드==//

    private void addMaterial(Material material) {
        materials.add(material);
        if(material.getProcess() != this) {
            material.setProcess(this);
        }
    }

    private void addAdditive(Additive additive) {
        additives.add(additive);
        if(additive.getProcess() != this) {
            additive.setProcess(this);
        }
    }

    //== 생성 메서드 ==//
//    public static Process createProcessWithDto(ProcessDto processDto, Member member) {
//        List<Material> materials = new ArrayList<>();
//        List<Additive> additives = new ArrayList<>();
//        if(processDto.getMaterials() != null){
//            for (MaterialDto materialDto : processDto.getMaterials()) {
//                materials.add(Material.createMaterialWithDto(materialDto));
//            }
//        }
//
//        if(processDto.getAdditives() != null) {
//            for (AdditiveDto additiveDto : processDto.getAdditives()) {
//                additives.add(Additive.createAdditiveWithDto(additiveDto));
//            }
//        }
//
//        return Process.createProcess(processDto.getDate(), processDto.getDailyProcessId(), processDto.getFurnaceNumber(), processDto.getAlloyCode(), processDto.getSize(), materials, additives, member);
//    }

    public static Process createProcess(LocalDate date, Integer dailyProcessId, Integer furnaceNumber, Integer alloyCode, Integer size,
                                        List<Material> materials, List<Additive> additives, Member member) {
        Process process = new Process(date, dailyProcessId, furnaceNumber,alloyCode, size, member);

        for (Material material : materials) {
            process.addMaterial(material);
        }
        for (Additive additive : additives) {
            process.addAdditive(additive);
        }

        return process;
    }

    public static Process createProcess(LocalDate date, Integer dailyProcessId, Integer furnaceNumber, Integer alloyCode, Integer size, Member member) {
        Process process = new Process(date, dailyProcessId, furnaceNumber,alloyCode, size, member);

        return process;
    }

    public void changeState(ProcessSimpleDto process) {
        this.date = process.getDate();
        this.dailyProcessId = process.getDailyProcessId();
        this.furnaceNumber = process.getFurnaceNumber();
        this.alloyCode = process.getAlloyCode();
        this.size = process.getSize();
    }

    public void changeState(ProcessDto processDto, FurnaceProcessUpdateService fpUpdateService) {

        // 기존에 저장되어있던 물질과 추가물중 수정과정에서 삭제된 것을 추척하여 컬렉션에서 삭제하고 쓰레기 데이터도 db에서 삭제함.
        List<Material> removedMaterials = fpUpdateService.traceRemovedMaterials(this.materials, processDto.getMaterials());
        for (Material removedMaterial : removedMaterials) {
            this.materials.remove(removedMaterial);
        }
        fpUpdateService.deleteRemovedMaterials(removedMaterials);

        List<Additive> removedAdditives = fpUpdateService.traceRemovedAdditives(this.additives, processDto.getAdditives());
        for (Additive removedAdditive : removedAdditives) {
            this.additives.remove(removedAdditive);
        }
        fpUpdateService.deleteRemovedAdditives(removedAdditives);

        this.date = processDto.getDate();
        this.dailyProcessId = processDto.getDailyProcessId();
        this.furnaceNumber = processDto.getFurnaceNumber();
        this.alloyCode = processDto.getAlloyCode();
        this.size = processDto.getSize();


        for (MaterialDto materialDto : processDto.getMaterials()) {
            for (Material material : this.materials) {
                if(material.getMaterialId() == materialDto.getMaterialId()) {
                    material.changeState(materialDto);
                    break;
                }
            }
        }

        for (MaterialDto materialDto : processDto.getMaterials()) {
            if (materialDto.getMaterialId() == null) {
                this.materials.add(MaterialDto.toEntity(materialDto, this));
            }
        }


        for (AdditiveDto additiveDto : processDto.getAdditives()) {
            for (Additive additive : this.additives) {
                if(additive.getAdditiveId() == additiveDto.getAdditiveId()) {
                    additive.changeState(additiveDto);
                    break;
                }
            }
        }

        for (AdditiveDto additiveDto : processDto.getAdditives()) {
            if (additiveDto.getAdditiveId() == null) {
                this.additives.add(AdditiveDto.toEntity(additiveDto, this));
            }
        }

    }

    //== 조회 로직 ==//

    /**
     * 총 투입량: 원자재 총합(인코드 + 스크랩)
     */
    public long getTotalMaterialAmount(){
        long totalAmount = 0L;
        for (Material material : materials) {
            totalAmount += material.getWeight();
        }

        return totalAmount;
    }


}
