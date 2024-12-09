package com.muci.framework.gateway.filter;

import com.muci.framework.common.constant.RequestIPConstant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RequestIPFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header(RequestIPConstant.RequestIP,exchange.getRequest().getRemoteAddress().getHostString())
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }
}
