package com.muci.framework.common.util;

import java.util.function.BiConsumer;

import com.muci.framework.common.context.TokenUserContext;
import com.muci.framework.common.entity.TokenUser;

import cn.hutool.core.util.ObjectUtil;

public class TokenUserFiller {
    public static <T> void fillUserId(T entity, BiConsumer<T, Integer> setter) {
        TokenUser tokenUser = TokenUserContext.getContext();
        if (ObjectUtil.isNotNull(tokenUser))
            setter.accept(entity, tokenUser.getUserId());
    }

    public static Integer getUserId() {
        TokenUser tokenUser = TokenUserContext.getContext();
        if (ObjectUtil.isNotNull(tokenUser))
            return tokenUser.getUserId();
        return null;
    }
}
