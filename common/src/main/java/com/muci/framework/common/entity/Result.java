package com.muci.framework.common.entity;

import com.muci.framework.common.enums.ResultCode;

import lombok.Data;

@Data
public class Result<T> {
    private Integer status;
    private String error;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setStatus(ResultCode.OK.getStatus());
        result.setData(data);
        result.setMessage(ResultCode.OK.getMessage());
        return result;
    }

    public static <T> Result<T> fail(ResultCode statusCode, String message) {
        Result<T> result = new Result<>();
        result.setStatus(statusCode.getStatus());
        result.setError(statusCode.getMessage());
        result.setMessage(message);
        return result;
    }

    public static Result fail(int status, String error, String message) {
        Result result = new Result<>();
        result.setStatus(status);
        result.setError(error);
        result.setMessage(message);
        return result;
    }
}
