package com.muci.framework.common.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.muci.framework.common.constant.TokenUserConstant;
import com.muci.framework.common.context.TokenUserContext;
import com.muci.framework.common.entity.TokenUser;

import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenUserInterceptor implements HandlerInterceptor {
    @Override
    @SneakyThrows
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tokenUser = request.getHeader(TokenUserConstant.Authorization);
        log.info("preHandle.tokenUser : {}", tokenUser);
        if (ObjectUtil.isNotNull(tokenUser)) {
            TokenUser userInfo = JSON.parseObject(tokenUser, TokenUser.class);
            TokenUserContext.setContext(userInfo);
        }
        return true;
    }

    @Override
    @SneakyThrows
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable Exception ex) {
        TokenUserContext.remove();
    }
}