package com.muci.framework.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.ResultCode;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ResponseFilter implements GlobalFilter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    private static void writeResult(ServerHttpRequest request, ServerHttpResponse response, Result result) {
        response.getStatusCode();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        byte[] resultBytes = objectMapper.writeValueAsBytes(result);
        DataBuffer dataBuffer = response.bufferFactory().wrap(resultBytes);
        response.writeWith(Mono.just(dataBuffer)).subscribe();
    }

    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).doOnSuccess(aVoid -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            HttpStatus statusCode = (HttpStatus)response.getStatusCode();

            ResultCode code = ResultCode.errorCode(statusCode.value());
            if (code != null) {
                writeResult(request, response, failResult(request, code));
            } else if (statusCode != HttpStatus.OK) {
                writeResult(request, response, failResult(request, statusCode));
            }
        });
    }

    private Result failResult(ServerHttpRequest request, ResultCode code) {
        return Result.fail(code, request.getPath().toString());
    }

    private Result failResult(ServerHttpRequest request, HttpStatus status) {
        return Result.fail(status.value(), status.getReasonPhrase(), request.getPath().toString());
    }
}
