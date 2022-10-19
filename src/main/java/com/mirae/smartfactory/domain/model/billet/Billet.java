package com.mirae.smartfactory.domain.model.billet;

import com.mirae.smartfactory.dto.BilletDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Billet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long billetId;

    private Long length;
    private Integer quantity;
    private Integer errorQuantity;
    private String errorReason;

    private Billet(Long length, Integer quantity, Integer errorQuantity, String errorReason) {
        this.length = length;
        this.quantity = quantity;
        this.errorQuantity = errorQuantity;
        this.errorReason = errorReason;
    }

    public static Billet createBilletWithDto(BilletDto billet) {
        return Billet.createBillet(billet.getLength(), billet.getQuantity(), billet.getErrorQuantity(), billet.getErrorReason());
    }

    public static Billet createBillet(Long length, Integer quantity, Integer errorQuantity, String errorReason) {
        return new Billet(length, quantity, errorQuantity, errorReason);
    }

    public void changeState(BilletDto billet) {
        this.length = billet.getLength();
        this.quantity = billet.getQuantity();
        this.errorQuantity = billet.getErrorQuantity();
        this.errorReason = billet.getErrorReason();
    }
}
