package com.mirae.smartfactory.domain.model.process.casting;

import com.mirae.smartfactory.domain.model.billet.Billet;
import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.dto.process.casting.CastingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Casting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long castingId;
    private LocalTime tappingStartTime;
    private LocalTime tappingEndTime;
    private String operator;
    private String shifter;

    private String remarks;
    @Setter
    @OneToOne(fetch = LAZY) //cascade 삭제(주조일지가 삭제되어도 process 테이블 데이터는 살아있어야 함.)
    @JoinColumn(name = "processId")
    private Process process;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "castingPreparationId")
    private CastingPreparation castingPreparation;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "castingDataId")
    private CastingData castingData;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "castingTemperatureId")
    private CastingTemperature castingTemperature;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "billetId")
    private Billet billet;

    private Casting(LocalTime tappingStartTime, LocalTime tappingEndTime, String operator, String shifter, String remarks, Process process, CastingPreparation castingPreparation, CastingData castingData, CastingTemperature castingTemperature, Billet billet) {
        this.tappingStartTime = tappingStartTime;
        this.tappingEndTime = tappingEndTime;
        this.operator = operator;
        this.shifter = shifter;
        this.remarks = remarks;
        this.process = process;
        this.castingPreparation = castingPreparation;
        this.castingData = castingData;
        this.castingTemperature = castingTemperature;
        this.billet = billet;
    }

    public void changeState(CastingDto cd) {
        this.tappingStartTime = cd.getTappingStartTime();
        this.tappingEndTime = cd.getTappingEndTime();
        this.operator = cd.getOperator();
        this.shifter = cd.getShifter();
        this.remarks = cd.getRemarks();
        this.process.changeState(cd.getProcess());
        this.castingPreparation.changeState(cd.getCastingPreparation());
        this.castingData.changeState(cd.getCastingData());
        this.castingTemperature.changeState(cd.getCastingTemperature());
        this.billet.changeState(cd.getBillet());
    }



    public static Casting createCastingWithDtoAndId(Long castingId, CastingDto cd, Process process, CastingPreparation castingPreparation,
                                                    CastingData castingData, CastingTemperature castingTemperature, Billet billet) {
        return Casting.createCastingWithDto(castingId, cd.getTappingStartTime(), cd.getTappingEndTime(),
                cd.getOperator(), cd.getShifter(), cd.getRemarks(), process,
                castingPreparation, castingData, castingTemperature, billet);
    }

    public static Casting createCastingWithDto(CastingDto cd, Process process, CastingPreparation castingPreparation,
                                               CastingData castingData, CastingTemperature castingTemperature, Billet billet) {
        return Casting.createCasting(cd.getTappingStartTime(), cd.getTappingEndTime(),
                cd.getOperator(), cd.getShifter(), cd.getRemarks(), process,
                castingPreparation, castingData, castingTemperature, billet);
    }


    public static Casting createCasting(LocalTime tappingStartTime, LocalTime tappingEndTime, String operator, String shifter, String remarks, Process process,
                                        CastingPreparation castingPreparation, CastingData castingData, CastingTemperature castingTemperature, Billet billet) {
        return new Casting(tappingStartTime, tappingEndTime, operator, shifter, remarks, process, castingPreparation, castingData, castingTemperature, billet);
    }

    public static Casting createCastingWithDto(Long castingId, LocalTime tappingStartTime, LocalTime tappingEndTime, String operator, String shifter, String remarks, Process process,
                                               CastingPreparation castingPreparation, CastingData castingData, CastingTemperature castingTemperature, Billet billet) {
        return new Casting(castingId, tappingStartTime, tappingEndTime, operator, shifter, remarks, process, castingPreparation, castingData, castingTemperature, billet);
    }


}
