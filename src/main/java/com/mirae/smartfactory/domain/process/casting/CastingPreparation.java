package com.mirae.smartfactory.domain.process.casting;

import com.mirae.smartfactory.dto.process.casting.CastingDto;
import com.mirae.smartfactory.dto.process.casting.CastingPreparationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CastingPreparation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long castingPreparationId;

    private Integer rotorSpeed1;
    private Integer rotorSpeed2;
    private Integer arAmount1;
    private Integer arAmount2;
    private Integer operatingDuration;
    private Integer outGassingTemperature;
    private Integer atbSpeed;
    private String castingPreparationMore;

    public CastingPreparation(Integer rotorSpeed1, Integer rotorSpeed2, Integer arAmount1, Integer arAmount2, Integer operatingDuration, Integer outGassingTemperature, Integer atbSpeed, String castingPreparationMore) {
        this.rotorSpeed1 = rotorSpeed1;
        this.rotorSpeed2 = rotorSpeed2;
        this.arAmount1 = arAmount1;
        this.arAmount2 = arAmount2;
        this.operatingDuration = operatingDuration;
        this.outGassingTemperature = outGassingTemperature;
        this.atbSpeed = atbSpeed;
        this.castingPreparationMore = castingPreparationMore;
    }

    public static CastingPreparation createCastingPreparationWithDto(CastingPreparationDto cpd) {
        return CastingPreparation.createCastingPreparation(cpd.getRotorSpeed1(), cpd.getRotorSpeed2(), cpd.getArAmount1(), cpd.getArAmount2(),
                cpd.getOperatingDuration(), cpd.getOutGassingTemperature(), cpd.getAtbSpeed(), cpd.getCastingPreparationMore());
    }

    public static CastingPreparation createCastingPreparation(Integer rotorSpeed1, Integer rotorSpeed2, Integer arAmount1, Integer arAmount2, Integer operatingDuration, Integer outGassingTemperature, Integer atbSpeed, String castingPreparationMore) {
        return new CastingPreparation(rotorSpeed1, rotorSpeed2, arAmount1, arAmount2,operatingDuration, outGassingTemperature, atbSpeed, castingPreparationMore);
    }
}
