package com.muci.framework.common.exception;

import com.muci.framework.common.entity.Result;

import lombok.Getter;

@Getter
public class FeignRPCException extends RuntimeException {
    private Result result;

    public FeignRPCException(Result result) {
        super("FeignRPC Fail");
        this.result = result;
    }
}
