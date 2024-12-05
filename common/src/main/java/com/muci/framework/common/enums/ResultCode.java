package com.muci.framework.common.enums;

import lombok.Getter;

@Getter
public enum ResultCode {

    OK(200, "Success"), BAD_REQUEST(400, "Bad Request"), UNAUTHORIZED(401, "Unauthorized"), FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"), CONFLICT(409, "Conflict"), INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable");

    public final int status;

    public final String message;

    ResultCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResultCode errorCode(int status) {
        for (ResultCode code : ResultCode.values()) {
            if (code.getStatus() != 200 && code.getStatus() == status) {
                return code;
            }
        }
        return null; // 如果没有匹配的status，可以返回null或者一个默认值
    }
}