package com.mirae.smartfactory.dto.result;

import com.mirae.smartfactory.consts.ConditionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuccessResult<T> {
    private String code;
    private String message;
    private T result;

    public SuccessResult(ConditionCode conditionCode, T result) {
        this.code = conditionCode.getCode();
        this.message = conditionCode.getMessage();
        this.result = result;
    }
}
