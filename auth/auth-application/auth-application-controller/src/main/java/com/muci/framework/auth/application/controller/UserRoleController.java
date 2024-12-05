package com.muci.framework.auth.application.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.application.convert.UserRoleDTOConverter;
import com.muci.framework.auth.application.dto.request.userRole.UserRoleOperateReq;
import com.muci.framework.auth.application.dto.request.userRole.UserRoleSearchReq;
import com.muci.framework.auth.domain.bo.UserRoleBO;
import com.muci.framework.auth.domain.bo.UserRoleOperateBO;
import com.muci.framework.auth.domain.bo.UserRoleSearchBO;
import com.muci.framework.auth.domain.service.UserRoleDomainService;
import com.muci.framework.common.convert.PageInfoConverter;
import com.muci.framework.common.entity.PageInfo;
import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("userRole")
public class UserRoleController {

    @Autowired
    private UserRoleDomainService userRoleDomainService;

    @PostMapping
    public Result addUserRole(@RequestBody UserRoleOperateReq userRoleAddReq) {
        try {
            log.info("addUserRole.userRoleAddReq : {}", JSON.toJSONString(userRoleAddReq));
            UserRoleOperateBO userRoleOperateBO = UserRoleDTOConverter.INSTANCE.convertToBO(userRoleAddReq);
            return Result.success(userRoleDomainService.addUserRole(userRoleOperateBO));
        } catch (DuplicateKeyException e) {
            log.error("addUserRole.DuplicateKeyException : ", e);
            return Result.fail(ResultCode.BAD_REQUEST, "请勿重复添加用户角色");
        } catch (DataIntegrityViolationException e) {
            log.error("addUserRole.DataIntegrityViolationException : ", e);
            return Result.fail(ResultCode.BAD_REQUEST, "不存在的用户或角色");
        }
    }

    @DeleteMapping
    public Result deleteUserRole(@RequestBody UserRoleOperateReq userRoleDeleteReq) {
        log.info("deleteUserRole.userRoleDeleteReq : {}", JSON.toJSONString(userRoleDeleteReq));
        UserRoleOperateBO userRoleOperateBO = UserRoleDTOConverter.INSTANCE.convertToBO(userRoleDeleteReq);
        return Result.success(userRoleDomainService.deleteUserRole(userRoleOperateBO));
    }

    @PostMapping("search")
    public Result findUserRoles(@RequestBody UserRoleSearchReq userRoleSearchReq) {
        UserRoleSearchBO userRoleSearchBO = UserRoleDTOConverter.INSTANCE.convertToBO(userRoleSearchReq);
        PageInfo pageInfo = PageInfoConverter.INSTANCE.convert(userRoleSearchReq);
        log.info("findUserRoles.userRoleSearchBO : {}", JSON.toJSONString(userRoleSearchBO));
        log.info("findUserRoles.pageInfo : {}", JSON.toJSONString(pageInfo));
        if (pageInfo.limited()) {
            IPage<UserRoleBO> userRoleBOList = userRoleDomainService.findUserRoles(userRoleSearchBO, pageInfo);
            return Result.success(userRoleBOList.convert(UserRoleDTOConverter.INSTANCE::convertToRes));
        } else {
            List<UserRoleBO> userRoleBOList = userRoleDomainService.findUserRoles(userRoleSearchBO);
            return Result.success(UserRoleDTOConverter.INSTANCE.convertToRes(userRoleBOList));
        }
    }
}
