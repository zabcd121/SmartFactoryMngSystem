package com.mirae.smartfactory.domain.resource;

import com.mirae.smartfactory.domain.furnace.Process;
import lombok.Getter;
import javax.persistence.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Material {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long materialId;

    private MaterialType materialType;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "materialNameId")
    private MaterialName materialName;

    private long weight;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "processId")
    private Process process;
}
