package com.muci.framework.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.muci.framework.common.constant.TokenUserConstant;
import com.muci.framework.common.entity.LoginUser;
import com.muci.framework.common.entity.TokenUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TokenFilter implements GlobalFilter {
    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        log.info("filter.url:{}", url);
        if (url.equals("/auth/user/doLogin"))
            return chain.filter(exchange);
        String token;
        try {
            token = StpUtil.getTokenValue();
        } catch (Exception e) {
            return chain.filter(exchange);
        }
        log.info("filter.token : {}", token);
        if (token != null && ObjectUtil.isNotNull(StpUtil.getExtra("loginUser"))) {
            LoginUser loginUser = JSON.parseObject(StpUtil.getExtra("loginUser").toString(), LoginUser.class);
            TokenUser tokenUser = new TokenUser(loginUser.getUserId(), loginUser.getEmployeeId());
            log.info("filter.tokenUser : {}", tokenUser);
            mutate.header(TokenUserConstant.Authorization, JSON.toJSONString(tokenUser));
        }
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}