package com.muci.framework.common.context;

import com.muci.framework.common.entity.TokenUser;

import lombok.Data;

@Data
public class TokenUserContext {
    private static final ThreadLocal<TokenUser> tokenUser = new ThreadLocal<>();

    public static TokenUser getContext() {
        return tokenUser.get();
    }

    public static void setContext(TokenUser tokenUserContext) {
        tokenUser.set(tokenUserContext);
    }

    public static void remove() {
        tokenUser.remove();
    }

    public static Integer getUserId() {
        return getContext().getUserId();
    }
}
