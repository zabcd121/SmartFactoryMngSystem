package com.mirae.smartfactory.dto.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuccessResult<T> {
    private String code;
    private String message;
    private T result;
}
