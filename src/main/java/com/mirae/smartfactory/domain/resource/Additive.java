package com.mirae.smartfactory.domain.resource;

import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.dto.AdditiveDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Additive {

    @Enumerated(value = EnumType.STRING)
    private final ResourceType resourceType = ResourceType.SI;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long additiveId;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processId")
    private Process process;

    private String additiveName;
    private long additiveWeight;

    private Additive(String additiveName, long additiveWeight) {
        this.additiveName = additiveName;
        this.additiveWeight = additiveWeight;
    }

//    public void setProcess(Process process) {
//        this.process = process;
//        process.getAdditives().add(this);
//    }

    //== 생성 메서드 ==/
    public static Additive createAdditiveWithDto(AdditiveDto additiveDto) {
        return Additive.createAdditive(additiveDto.getAdditiveName(), additiveDto.getAdditiveWeight());
    }

    public static Additive createAdditive(String additiveName, long additiveWeight) {
        Additive additive = new Additive(additiveName, additiveWeight);

        return additive;
    }
}
