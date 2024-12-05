package com.muci.framework.auth.application.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.application.convert.RoleDTOConverter;
import com.muci.framework.auth.application.dto.request.role.RoleAddReq;
import com.muci.framework.auth.application.dto.request.role.RoleSearchReq;
import com.muci.framework.auth.application.dto.request.role.RoleUpdateReq;
import com.muci.framework.auth.domain.bo.RoleBO;
import com.muci.framework.auth.domain.bo.RoleSearchBO;
import com.muci.framework.auth.domain.service.RoleDomainService;
import com.muci.framework.common.convert.PageInfoConverter;
import com.muci.framework.common.entity.PageInfo;
import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleDomainService roleDomainService;

    @PostMapping
    public Result addRole(@RequestBody RoleAddReq roleAddReq) {
        try {
            log.info("addRole.roleAddReq : {}", JSON.toJSONString(roleAddReq));
            RoleBO roleBO = RoleDTOConverter.INSTANCE.convertToBO(roleAddReq);
            RoleBO resRole = roleDomainService.addRole(roleBO);
            return Result.success(RoleDTOConverter.INSTANCE.convertToRes(resRole));
        } catch (DuplicateKeyException e) {
            log.error("addRoleMenu.DuplicateKeyException : ", e);
            return Result.fail(ResultCode.CONFLICT, "请勿重复添加同名或同关键词角色");
        }
    }

    @DeleteMapping("{roleId}")
    public Result deleteRole(@PathVariable("roleId") Integer roleId) {
        log.info("deleteRole.roleId : {}", roleId);
        return Result.success(roleDomainService.deleteRole(roleId));
    }

    @PutMapping("{roleId}")
    public Result updateRole(@PathVariable("roleId") Integer roleId, @RequestBody RoleUpdateReq roleUpdateReq) {
        try {
            log.info("updateRole.roleId : {}", roleId);
            log.info("updateRole.roleUpdateReq : {}", JSON.toJSONString(roleUpdateReq));
            RoleBO roleBO = RoleDTOConverter.INSTANCE.convertToBO(roleUpdateReq);
            roleBO.setRoleId(roleId);
            RoleBO resRole = roleDomainService.updateRole(roleBO);
            return Result.success(RoleDTOConverter.INSTANCE.convertToRes(resRole));
        } catch (DuplicateKeyException e) {
            log.error("updateRole.DuplicateKeyException : {}", e.getMessage());
            return Result.fail(ResultCode.BAD_REQUEST, "关键信息重复");
        }
    }

    @GetMapping("{roleId}")
    public Result findRoleById(@PathVariable("roleId") Integer roleId) {
        log.info("findRoleById.roleId : {}", roleId);
        RoleBO role = roleDomainService.findRole(roleId);
        return Result.success(RoleDTOConverter.INSTANCE.convertToRes(role));
    }

    @PostMapping("search")
    public Result findRoles(@RequestBody RoleSearchReq roleSearchReq) {
        RoleSearchBO roleSearchBO = RoleDTOConverter.INSTANCE.convertToBO(roleSearchReq);
        PageInfo page = PageInfoConverter.INSTANCE.convert(roleSearchReq);
        log.info("findRoles.roleSearchBO : {}", JSON.toJSONString(roleSearchBO));
        log.info("findRoles.page : {}", JSON.toJSONString(page));
        if (page.limited()) {
            IPage<RoleBO> roleBOList = roleDomainService.findRoles(roleSearchBO, page);
            return Result.success(roleBOList.convert(RoleDTOConverter.INSTANCE::convertToRes));
        } else {
            List<RoleBO> roleBOList = roleDomainService.findRoles(roleSearchBO);
            return Result.success(RoleDTOConverter.INSTANCE.convertToRes(roleBOList));
        }
    }

    @PostMapping("{roleId}/menu")
    public Result findRoleMenuById(@PathVariable("roleId") Integer roleId) {
        log.info("findRoleMenuById.roleId : {}", roleId);
        return Result.success(roleDomainService.findRoleMenu(roleId));
    }
}
