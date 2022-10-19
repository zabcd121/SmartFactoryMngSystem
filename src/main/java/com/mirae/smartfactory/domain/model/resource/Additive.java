package com.mirae.smartfactory.domain.model.resource;

import com.mirae.smartfactory.domain.model.process.Process;
import com.mirae.smartfactory.dto.resource.AdditiveDto;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Additive {

    @Enumerated(value = EnumType.STRING)
    private final ResourceType resourceType = ResourceType.SI;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long additiveId;

    private Integer sequence;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processId")
    private Process process;

    private String additiveName;
    private Integer additiveWeight;

    private Additive(Integer sequence, String additiveName, Integer additiveWeight, Process process) {
        this.sequence = sequence;
        this.additiveName = additiveName;
        this.additiveWeight = additiveWeight;
        this.process = process;
    }

    private Additive(Integer sequence, String additiveName, Integer additiveWeight) {
        this.sequence = sequence;
        this.additiveName = additiveName;
        this.additiveWeight = additiveWeight;
    }

    public void changeState(AdditiveDto additiveDto) {
        this.sequence = additiveDto.getSequence();
        this.additiveName = additiveDto.getAdditiveName();
        this.additiveWeight = additiveDto.getAdditiveWeight();
    }

    //== 생성 메서드 ==/
//    public static Additive createAdditiveWithDto(AdditiveDto additiveDto) {
//        return Additive.createAdditive(additiveDto.getAdditiveName(), additiveDto.getAdditiveWeight();
//    }

    public static Additive createAdditive(Integer sequence, String additiveName, Integer additiveWeight, Process process) {
        Additive additive = new Additive(sequence, additiveName, additiveWeight, process);

        return additive;
    }

    public static Additive createAdditive(Integer sequence, String additiveName, Integer additiveWeight) {
        Additive additive = new Additive(sequence, additiveName, additiveWeight);

        return additive;
    }
}
