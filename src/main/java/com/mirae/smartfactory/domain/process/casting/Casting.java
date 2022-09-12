package com.mirae.smartfactory.domain.process.casting;

import com.mirae.smartfactory.domain.billet.Billet;
import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.dto.process.casting.CastingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Casting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long castingId;
    private LocalDateTime tappingStartTime;
    private LocalDateTime tappingEndTime;
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

    private Casting(LocalDateTime tappingStartTime, LocalDateTime tappingEndTime, String operator, String shifter, String remarks, Process process, CastingPreparation castingPreparation, CastingData castingData, CastingTemperature castingTemperature, Billet billet) {
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


    public static Casting createCasting(LocalDateTime tappingStartTime, LocalDateTime tappingEndTime, String operator, String shifter, String remarks, Process process,
                                        CastingPreparation castingPreparation, CastingData castingData, CastingTemperature castingTemperature, Billet billet) {
        return new Casting(tappingStartTime, tappingEndTime, operator, shifter, remarks, process, castingPreparation, castingData, castingTemperature, billet);
    }

    public static Casting createCastingWithDto(Long castingId, LocalDateTime tappingStartTime, LocalDateTime tappingEndTime, String operator, String shifter, String remarks, Process process,
                                               CastingPreparation castingPreparation, CastingData castingData, CastingTemperature castingTemperature, Billet billet) {
        return new Casting(castingId, tappingStartTime, tappingEndTime, operator, shifter, remarks, process, castingPreparation, castingData, castingTemperature, billet);
    }


}
