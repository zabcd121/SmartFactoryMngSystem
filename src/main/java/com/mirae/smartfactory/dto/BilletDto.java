package com.mirae.smartfactory.dto;

import com.mirae.smartfactory.domain.billet.Billet;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BilletDto {

    private Long length;
    private Integer quantity;
    private Integer errorQuantity;
    private String errorReason;

    public BilletDto(Billet billet) {
        this.length = billet.getLength();
        this.quantity = billet.getQuantity();
        this.errorQuantity = billet.getErrorQuantity();
        this.errorReason = billet.getErrorReason();
    }
}
