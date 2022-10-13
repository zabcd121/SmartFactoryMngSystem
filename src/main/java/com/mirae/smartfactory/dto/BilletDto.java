package com.mirae.smartfactory.dto;

import com.mirae.smartfactory.domain.model.billet.Billet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BilletDto {
    private Long billetId;
    private Long length;
    private Integer quantity;
    private Integer errorQuantity;
    private String errorReason;

    public BilletDto(Billet billet) {
        this.billetId = billet.getBilletId();
        this.length = billet.getLength();
        this.quantity = billet.getQuantity();
        this.errorQuantity = billet.getErrorQuantity();
        this.errorReason = billet.getErrorReason();
    }
}
