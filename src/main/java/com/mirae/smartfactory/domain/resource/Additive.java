package com.mirae.smartfactory.domain.resource;

import com.mirae.smartfactory.domain.furnace.Process;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Additive {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long additiveId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processId")
    private Process process;

    private AdditiveName additiveName;
    private Long additiveWeight;
}
