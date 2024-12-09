package com.muci.framework.common.context;

import lombok.Data;

@Data
public class RequestIPContext {
    private static final ThreadLocal<String> requestIP = new ThreadLocal<>();

    public static String getContext() {
        return requestIP.get();
    }

    public static void setContext(String ipAddress) {
        requestIP.set(ipAddress);
    }

    public static void remove() {
        requestIP.remove();
    }
}
