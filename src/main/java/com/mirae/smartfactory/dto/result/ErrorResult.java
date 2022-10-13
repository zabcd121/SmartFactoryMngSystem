package com.mirae.smartfactory.dto.result;

import com.mirae.smartfactory.consts.ConditionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResult {
    private String code;
    private String message;

    public ErrorResult(ConditionCode conditionCode) {
        this.code = conditionCode.getCode();
        this.message = conditionCode.getMessage();
    }
}
