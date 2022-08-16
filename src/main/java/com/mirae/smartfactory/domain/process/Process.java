package com.mirae.smartfactory.domain.process;

import com.mirae.smartfactory.domain.billet.Billet;
import com.mirae.smartfactory.domain.process.casting.Casting;
import com.mirae.smartfactory.domain.process.furnace.FurnaceProcess;
import com.mirae.smartfactory.domain.resource.Additive;
import com.mirae.smartfactory.domain.resource.Material;
import com.mirae.smartfactory.domain.resource.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

/**
 * 체크o
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Process {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long processId;

    private LocalDate date;

    private int dailyProcessId;

    private int furnaceNumber;

    private int alloyCode;

    private int size;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<Material> materials = new ArrayList<>();

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<Additive> additives = new ArrayList<>();


    @OneToOne(mappedBy = "process", fetch = LAZY, cascade = CascadeType.ALL)
    private FurnaceProcess furnaceProcess;

    @OneToOne(mappedBy = "process", fetch = LAZY, cascade = CascadeType.ALL)
    private Casting casting;

    @OneToOne(mappedBy = "process", fetch = LAZY, cascade = CascadeType.ALL)
    private Billet billet;

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

    public void setFurnaceProcess(FurnaceProcess furnaceProcess) {
        this.furnaceProcess = furnaceProcess;
        furnaceProcess.setProcess(this);
    }

    private void setCasting(Casting casting) {
        this.casting = casting;
        casting.setProcess(this);
    }

    private void setBillet(Billet billet) {
        this.billet = billet;
        billet.setProcess(this);
    }

    //== 생성 메서드 ==//

    public static Process createProcess(LocalDate date, int dailyProcessId, int furnaceNumber, int alloyCode, int size,
                                        List<Material> materials, List<Additive> additives, FurnaceProcess furnaceProcess,
                                        Casting casting, Billet billet, Member member) {
        Process process = new Process(date, dailyProcessId, furnaceNumber,alloyCode, size, member);

        for (Material material : materials) {
            process.addMaterial(material);
        }
        for (Additive additive : additives) {
            process.addAdditive(additive);
        }
        process.setFurnaceProcess(furnaceProcess);
        process.setCasting(casting);
        process.setBillet(billet);

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
