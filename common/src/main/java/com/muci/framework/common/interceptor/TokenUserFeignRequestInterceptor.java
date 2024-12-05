package com.muci.framework.common.interceptor;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.muci.framework.common.constant.TokenUserConstant;
import com.muci.framework.common.context.TokenUserContext;
import com.muci.framework.common.entity.TokenUser;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenUserFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 从应用上下文中取出user信息，放入Feign的请求头中
        TokenUser user = TokenUserContext.getContext();
        log.info("apply.user : {}", JSON.toJSONString(user));
        if (user != null)
            requestTemplate.header(TokenUserConstant.Authorization, JSON.toJSONString(user));
    }
}