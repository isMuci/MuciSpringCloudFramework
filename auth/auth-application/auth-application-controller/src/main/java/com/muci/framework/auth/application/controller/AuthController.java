package com.muci.framework.auth.application.controller;

import cn.dev33.satoken.exception.SaTokenException;
import com.alibaba.fastjson2.JSON;
import com.muci.framework.auth.application.convert.AuthConverter;
import com.muci.framework.auth.application.dto.request.auth.LoginReq;
import com.muci.framework.auth.domain.service.UserDomainService;
import com.muci.framework.common.entity.LoginUser;
import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.ResultCode;
import com.muci.framework.common.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private UserDomainService userDomainService;

    // @PostMapping("register")
    public Result register(@RequestBody LoginReq loginReq) {
        // try {
        // log.info("register.loginReq : {}", JSON.toJSONString(userDTO));
        // LoginUser userBO = UserDTOConverter.INSTANCE.convertToBO(userDTO);
        // return Result.success(userDomainService.addUser(userBO));
        // } catch (DuplicateKeyException e) {
        // log.error("register.DuplicateKeyException : ", e);
        // return Result.fail(ResultCode.BAD_REQUEST, "用户名已被使用");
        // }
        return null;
    }

    @PostMapping("doLogin")
    public Result doLogin(@RequestBody LoginReq loginReq) {
        try {
            log.info("doLogin.loginReq:{}", JSON.toJSONString(loginReq));
            LoginUser loginBO = AuthConverter.INSTANCE.convertToBO(loginReq);
            LoginUser resLogin = userDomainService.doLogin(loginBO);
            return Result.success(AuthConverter.INSTANCE.convertToRes(resLogin));
        } catch (SaTokenException e) {
            log.error("doLogin.SaTokenException : ", e);
            return Result.fail(ResultCode.UNAUTHORIZED, e.getMessage());
        } catch (UnauthorizedException e) {
            log.error("doLogin.UnauthorizedDeviceException : ", e);
            return Result.fail(ResultCode.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("isLogin")
    public Result isLogin() {
        return Result.success(userDomainService.isLogin());
    }
}
