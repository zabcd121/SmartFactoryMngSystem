package com.mirae.smartfactory.exception.advice;

import com.mirae.smartfactory.dto.result.ErrorResult;
import com.mirae.smartfactory.exception.InvalidPWException;
import com.mirae.smartfactory.exception.NotExistIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class MemberControllerAdvice {
    private final static String ID_NULL_CODE = "0101";
    private final static String PW_NULL_CODE = "0102";
    private final static String NOT_EXIST_ID_CODE = "0102";
    private final static String INVALID_PW_CODE = "0103";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult invalidPWExHandler(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String field = "";
        String message = "";
        for (ObjectError e : allErrors) {
            FieldError fe = (FieldError) e;
            field = fe.getField();
            message = fe.getDefaultMessage();
            break;
        }
        if(field == "loginId") return new ErrorResult(ID_NULL_CODE, message);
        else return new ErrorResult(PW_NULL_CODE, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult notExistExHandler(NotExistIdException ex) {
        log.error("[exceptionHandler] member", ex);
        return new ErrorResult(NOT_EXIST_ID_CODE, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResult invalidPWExHandler(InvalidPWException ex) {
        log.error("[exceptionHandler] member", ex);
        return new ErrorResult(INVALID_PW_CODE, ex.getMessage());
    }
}
