package com.mirae.smartfactory.domain.process;

import com.mirae.smartfactory.domain.resource.Additive;
import com.mirae.smartfactory.domain.resource.Material;
import com.mirae.smartfactory.domain.resource.Member;
import com.mirae.smartfactory.dto.process.ProcessDto;
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

    public Process(LocalDate date, int dailyProcessId, int furnaceNumber, int alloyCode, int size, Member member) {
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
        material.setProcess(this);
    }

    private void addAdditive(Additive additive) {
        additives.add(additive);
        additive.setProcess(this);
    }

    //== 생성 메서드 ==//
    public static Process createProcessWithDto(ProcessDto processDto, Member member) {
        List<Material> materials = new ArrayList<>();
        List<Additive> additives = new ArrayList<>();
        for (MaterialDto materialDto : processDto.getMaterials()) {
            materials.add(Material.createMaterialWithDto(materialDto));
        }
        for (AdditiveDto additiveDto : processDto.getAdditives()) {
            additives.add(Additive.createAdditiveWithDto(additiveDto));
        }

        return Process.createProcess(processDto.getDate(), processDto.getDailyProcessId(), processDto.getFurnaceNumber(), processDto.getAlloyCode(), processDto.getSize(), materials, additives, member);
    }

    public static Process createProcess(LocalDate date, int dailyProcessId, int furnaceNumber, int alloyCode, int size,
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
