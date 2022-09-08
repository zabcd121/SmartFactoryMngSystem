package com.mirae.smartfactory.exception.advice;

import com.mirae.smartfactory.controller.MemberApiController;
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

import static com.mirae.smartfactory.consts.DomainConditionCode.*;

@Slf4j
@RestControllerAdvice(assignableTypes = {MemberApiController.class})
public class MemberControllerAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult emptyLoginArgument(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String field = "";
        String message = "";
        for (ObjectError e : allErrors) {
            FieldError fe = (FieldError) e;
            field = fe.getField();
            message = fe.getDefaultMessage();
            break;
        }
        log.info("emptyLoginArgument ex {}", ex);
        log.info("field, {}", field);
        if(field.equals("loginId")) return new ErrorResult(ID_NULL_CODE, message);
        else return new ErrorResult(PW_NULL_CODE, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult notExistIdExHandler(NotExistIdException ex) {
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
