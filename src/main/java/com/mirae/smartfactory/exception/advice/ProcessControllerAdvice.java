package com.mirae.smartfactory.exception.advice;

import com.mirae.smartfactory.controller.process.CastingProcessApiController;
import com.mirae.smartfactory.controller.process.FurnaceProcessApiController;
import com.mirae.smartfactory.dto.result.ErrorResult;
import com.mirae.smartfactory.exception.NotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.mirae.smartfactory.consts.DomainConditionCode.*;

@Slf4j
@RestControllerAdvice(assignableTypes = {FurnaceProcessApiController.class, CastingProcessApiController.class})
public class ProcessControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult notExist(NotExistException ex) {
        log.error("[exceptionHandler] process delete", ex);
        return new ErrorResult(FURNACEPROCESS_DELETE_FAIL, ex.getMessage());
    }
}
