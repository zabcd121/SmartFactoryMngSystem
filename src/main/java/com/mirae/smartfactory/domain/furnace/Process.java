package com.mirae.smartfactory.domain.furnace;

import com.mirae.smartfactory.domain.billet.Billet;
import com.mirae.smartfactory.domain.casting.Casting;
import com.mirae.smartfactory.domain.resource.Additive;
import com.mirae.smartfactory.domain.resource.Material;
import com.mirae.smartfactory.domain.resource.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

/**
 * 체크o
 */
@Entity
@Getter
public class Process {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long processId;

    private LocalDateTime date;
    private int dailyProcessId;
    private int furnaceNumber;
    private AlloyCode alloyCode;
    private int size;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<Material> materials = new ArrayList<>();

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL)
    private List<Additive> additives = new ArrayList<>();


    @OneToOne(mappedBy = "process", fetch = LAZY, cascade = CascadeType.ALL)
    private FurnaceProcess furnaceProcess;

    @OneToOne(mappedBy = "process", fetch = LAZY, cascade = CascadeType.ALL)
    private Casting casting;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="billetId")
    private Billet billet;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

}
