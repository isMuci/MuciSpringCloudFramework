package com.muci.framework.gateway.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.ResultCode;
import feign.FeignException;
import lombok.SneakyThrows;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;

@Component
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static ResultCode matchResultCode(Throwable ex) {
        if (ex instanceof NotLoginException) {
            return ResultCode.UNAUTHORIZED;
        } else if (ex instanceof NotPermissionException) {
            return ResultCode.FORBIDDEN;
        } else if (ex instanceof NotFoundException || ex instanceof FileNotFoundException) {
            return ResultCode.NOT_FOUND;
        } else if (ex instanceof FeignException.ServiceUnavailable) {
            return ResultCode.SERVICE_UNAVAILABLE;
        } else if (ex instanceof HttpClientErrorException.BadRequest) {
            return ResultCode.BAD_REQUEST;
        } else {
            return ResultCode.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    @SneakyThrows
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ex = ex.getCause() == null ? ex : ex.getCause();
        System.out.println(ex);
        ex.printStackTrace();
        ResultCode resultCode = matchResultCode(ex);
        System.out.println(resultCode);
        Result result = Result.fail(resultCode, ex.getMessage());
        response.setRawStatusCode(result.getStatus());
        byte[] resultBytes = objectMapper.writeValueAsBytes(result);
        DataBuffer dataBuffer = response.bufferFactory().wrap(resultBytes);
        return response.writeWith(Mono.just(dataBuffer));
    }
}