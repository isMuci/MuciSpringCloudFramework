package com.muci.framework.auth.application.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.application.convert.UserDTOConverter;
import com.muci.framework.auth.application.dto.request.user.UserAddReq;
import com.muci.framework.auth.application.dto.request.user.UserSearchReq;
import com.muci.framework.auth.application.dto.request.user.UserUpdateReq;
import com.muci.framework.auth.domain.bo.UserBO;
import com.muci.framework.auth.domain.bo.UserSearchBO;
import com.muci.framework.auth.domain.service.UserDomainService;
import com.muci.framework.common.convert.PageInfoConverter;
import com.muci.framework.common.entity.PageInfo;
import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.ResultCode;
import com.muci.framework.common.exception.RepeatPasswordException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDomainService userDomainService;

    @PostMapping
    public Result addUser(@Valid @RequestBody UserAddReq userAddReq) {
        try {
            log.info("addUser.userAddReq : {}", JSON.toJSONString(userAddReq));
            UserBO userBO = UserDTOConverter.INSTANCE.convertToBO(userAddReq);
            UserBO resUser = userDomainService.addUser(userBO);
            return Result.success(UserDTOConverter.INSTANCE.convertToRes(resUser));
        } catch (DuplicateKeyException e) {
            log.error("addRoleMenu.DuplicateKeyException : ", e);
            return Result.fail(ResultCode.CONFLICT, "请勿重复添加同名用户");
        }
    }

    @DeleteMapping("{userId}")
    public Result deleteUser(@PathVariable("userId") Integer userId) {
        log.info("deleteUser.userId : {}", userId);
        return Result.success(userDomainService.deleteUser(userId));
    }

    @PutMapping("{userId}")
    public Result updateUserById(@PathVariable("userId") Integer userId, @RequestBody UserUpdateReq userUpdateReq) {
        try {
            log.info("updateUser.userId : {}", userId);
            log.info("updateUser.userUpdateReq : {}", JSON.toJSONString(userUpdateReq));
            UserBO userBO = UserDTOConverter.INSTANCE.convertToBO(userUpdateReq);
            userBO.setUserId(userId);
            UserBO resUser = userDomainService.updateUser(userBO);
            return Result.success(UserDTOConverter.INSTANCE.convertToRes(resUser));
        } catch (RepeatPasswordException e) {
            log.error("updateUser.RepeatPasswordException : ", e);
            return Result.fail(ResultCode.BAD_REQUEST, "修改密码时请勿填写旧密码");
        } catch (DuplicateKeyException e) {
            log.error("updateUser.DuplicateKeyException : ", e);
            return Result.fail(ResultCode.BAD_REQUEST, "关键信息重复");
        }
    }

    @GetMapping("{userId}")
    public Result findUserById(@PathVariable("userId") Integer userId) {
        log.info("findUserById.userId : {}", userId);
        UserBO user = userDomainService.findUser(userId);
        return Result.success(UserDTOConverter.INSTANCE.convertToRes(user));
    }

    @PostMapping("search")
    public Result findUsers(@RequestBody UserSearchReq userSearchReq) {
        UserSearchBO userSearchBO = UserDTOConverter.INSTANCE.convertToBO(userSearchReq);
        PageInfo page = PageInfoConverter.INSTANCE.convert(userSearchReq);
        log.info("findUsers.userSearchBO:{}", JSON.toJSONString(userSearchBO));
        log.info("findUsers.page:{}", JSON.toJSONString(page));
        if (page.limited()) {
            IPage<UserBO> userBOList = userDomainService.findUsers(userSearchBO, page);
            return Result.success(userBOList.convert(UserDTOConverter.INSTANCE::convertToRes));
        } else {
            List<UserBO> userBOList = userDomainService.findUsers(userSearchBO);
            return Result.success(UserDTOConverter.INSTANCE.convertToRes(userBOList));
        }
    }

    @PostMapping("{userId}/role")
    public Result findUserRoleById(@PathVariable("userId") Integer userId) {
        log.info("findUserRoleById.userId : {}", userId);
        return Result.success(userDomainService.findUserRole(userId));
    }
}
