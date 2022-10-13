package com.mirae.smartfactory.dto.result;

import com.mirae.smartfactory.consts.ConditionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuccessNoResult {
    private String code;
    private String message;

    public SuccessNoResult(ConditionCode conditionCode) {
        this.code = conditionCode.getCode();
        this.message = conditionCode.getMessage();
    }
}
