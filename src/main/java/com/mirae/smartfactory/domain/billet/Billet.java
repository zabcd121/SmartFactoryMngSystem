package com.mirae.smartfactory.domain.billet;

import com.mirae.smartfactory.domain.furnace.Process;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Billet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long billetId;

    @OneToOne(mappedBy = "billet", fetch = LAZY)
    private Process process;

    private long length;
    private int quantity;
    private int errorQuantity;
    private String billetMore;
}
