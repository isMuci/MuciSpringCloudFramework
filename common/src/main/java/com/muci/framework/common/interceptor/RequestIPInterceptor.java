package com.muci.framework.common.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.muci.framework.common.constant.RequestIPConstant;
import com.muci.framework.common.constant.TokenUserConstant;
import com.muci.framework.common.context.RequestIPContext;
import com.muci.framework.common.context.TokenUserContext;
import com.muci.framework.common.entity.TokenUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class RequestIPInterceptor implements HandlerInterceptor {
    @Override
    @SneakyThrows
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestIP = request.getHeader(RequestIPConstant.RequestIP);
        log.info("preHandle.tokenUser : {}", requestIP);
        if (ObjectUtil.isNotNull(requestIP)) {
            RequestIPContext.setContext(requestIP);
        }
        return true;
    }

    @Override
    @SneakyThrows
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable Exception ex) {
        RequestIPContext.remove();
    }
}