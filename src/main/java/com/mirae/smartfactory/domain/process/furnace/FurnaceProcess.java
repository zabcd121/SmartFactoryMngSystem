package com.mirae.smartfactory.domain.furnace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class FurnaceProcess {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long furnaceProcessId;

    private LocalDateTime chargingTime;
    private LocalDateTime outGassingTime;
    private LocalDateTime beforeTappingTime;
    private long outGassingInput;
    private long inclusionsInput;
    private long dustAmount;
    private long dustOutGassingBefore;
    private long dustOutGassingAfter;
    private String shipTo;

    @JsonIgnore
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "processId")
    private Process process;

    @OneToOne(mappedBy = "furnaceProcess", fetch = LAZY, cascade = CascadeType.ALL)
    private IngredientAnalysis ingredientAnalysis;


    public void setProcess(Process process) {
        this.process = process;
    }
}
