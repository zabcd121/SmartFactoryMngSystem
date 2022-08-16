package com.mirae.smartfactory.domain.furnace;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class IngredientAnalysis {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ingredientAnalysisId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "furnaceProcessId")
    private FurnaceProcess furnaceProcess;

//    private int sequenceNumber;
    private long ai;
    private long si;
    private long fe;
    private long cu;
    private long mg;
    private long zn;
    private long mn;
}
