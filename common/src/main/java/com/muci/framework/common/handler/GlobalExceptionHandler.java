package com.muci.framework.common.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.ResultCode;
import com.muci.framework.common.exception.*;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public Result handleFeignServiceUnavailableException(FeignException.ServiceUnavailable e) {
        log.error("handleFeignServiceUnavailableException.FeignException.ServiceUnavailable : ", e);
        return Result.fail(ResultCode.SERVICE_UNAVAILABLE, e.getMessage());
    }

    @ExceptionHandler(FeignRPCException.class)
    public Result handleFeignRPCException(FeignRPCException e) {
        log.error("handleFeignRPCException.FeignRPCException : ", e);
        return e.getResult();
    }

    @ExceptionHandler(BadRequestException.class)
    public Result handleBadRequestException(BadRequestException e) {
        log.error("handleBadRequestException.BadRequestException : ", e);
        return Result.fail(ResultCode.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result handleUnauthorizedException(UnauthorizedException e) {
        log.error("handleUnauthorizedException.UnauthorizedException : ", e);
        return Result.fail(ResultCode.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public Result handleForbiddenException(ForbiddenException e) {
        log.error("handleForbiddenException.ForbiddenException : ", e);
        return Result.fail(ResultCode.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public Result handleNotFoundException(NotFoundException e) {
        log.error("handleNotFoundException.NotFoundException : ", e);
        return Result.fail(ResultCode.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        return Result.fail(ResultCode.BAD_REQUEST, error);
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return Result.fail(ResultCode.BAD_REQUEST, JSON.toJSONString(errors));

    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("handleException.Exception : ", e);
        return Result.fail(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
