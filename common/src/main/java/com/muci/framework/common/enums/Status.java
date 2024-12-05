package com.muci.framework.common.enums;

import lombok.Getter;

@Getter
public enum Status {
    OPEN(0, "启用"), CLOSE(1, "禁用");

    public int code;

    public String desc;

    Status(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
