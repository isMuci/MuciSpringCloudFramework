package com.muci.framework.common.enums;

import java.util.Date;

import lombok.Getter;

@Getter
public enum IsDel {
    DELETED(1, "已删除"), UN_DELETED(0, "未删除");

    public int code;

    public String desc;

    IsDel(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int time() {
        return (int)(new Date().getTime() / 1000);
    }
}
