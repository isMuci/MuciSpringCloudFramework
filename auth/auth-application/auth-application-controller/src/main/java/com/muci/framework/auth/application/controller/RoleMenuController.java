package com.muci.framework.auth.application.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.application.convert.RoleMenuDTOConverter;
import com.muci.framework.auth.application.dto.request.roleMenu.RoleMenuOperateReq;
import com.muci.framework.auth.application.dto.request.roleMenu.RoleMenuSearchReq;
import com.muci.framework.auth.domain.bo.RoleMenuBO;
import com.muci.framework.auth.domain.bo.RoleMenuOperateBO;
import com.muci.framework.auth.domain.bo.RoleMenuSearchBO;
import com.muci.framework.auth.domain.service.RoleMenuDomainService;
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

@RestController
@RequestMapping("roleMenu")
@Slf4j
public class RoleMenuController {

    @Autowired
    private RoleMenuDomainService roleMenuDomainService;

    @PostMapping
    public Result addRoleMenu(@RequestBody RoleMenuOperateReq roleMenuAddReq) {
        try {
            log.info("addRoleMenu.roleMenuAddReq : {}", JSON.toJSONString(roleMenuAddReq));
            RoleMenuOperateBO roleMenuOperateBO = RoleMenuDTOConverter.INSTANCE.convertToBO(roleMenuAddReq);
            return Result.success(roleMenuDomainService.addRoleMenu(roleMenuOperateBO));
        } catch (DuplicateKeyException e) {
            log.error("addRoleMenu.DuplicateKeyException : ", e);
            return Result.fail(ResultCode.BAD_REQUEST, "请勿重复添加角色菜单");
        } catch (DataIntegrityViolationException e) {
            log.error("addRoleMenu.DataIntegrityViolationException : ", e);
            return Result.fail(ResultCode.BAD_REQUEST, "不存在的角色或菜单");
        }
    }

    @DeleteMapping
    public Result deleteRoleMenu(@RequestBody RoleMenuOperateReq roleMenuDeleteReq) {
        try {
            log.info("deleteRoleMenu.roleMenuDeleteReq : {}", JSON.toJSONString(roleMenuDeleteReq));
            RoleMenuOperateBO roleMenuOperateBO = RoleMenuDTOConverter.INSTANCE.convertToBO(roleMenuDeleteReq);
            return Result.success(roleMenuDomainService.deleteRoleMenu(roleMenuOperateBO));
        } catch (DataIntegrityViolationException e) {
            log.error("addRoleMenu.DataIntegrityViolationException : ", e);
            return Result.fail(ResultCode.BAD_REQUEST, "不存在的角色或菜单");
        }
    }

    @PostMapping("search")
    public Result findRoleMenus(@RequestBody RoleMenuSearchReq roleMenuSearchReq) {
        RoleMenuSearchBO roleMenuSearchBO = RoleMenuDTOConverter.INSTANCE.convertToBO(roleMenuSearchReq);
        PageInfo pageInfo = PageInfoConverter.INSTANCE.convert(roleMenuSearchReq);
        log.info("findRoleMenus.roleMenuSearchReq : {}", JSON.toJSONString(roleMenuSearchReq));
        log.info("findRoleMenus.pageInfo : {}", JSON.toJSONString(pageInfo));
        if (pageInfo.limited()) {
            IPage<RoleMenuBO> roleMenuBOList = roleMenuDomainService.findRoleMenus(roleMenuSearchBO, pageInfo);
            return Result.success(roleMenuBOList.convert(RoleMenuDTOConverter.INSTANCE::convertToRes));
        } else {
            List<RoleMenuBO> roleMenuBOList = roleMenuDomainService.findRoleMenus(roleMenuSearchBO);
            return Result.success(RoleMenuDTOConverter.INSTANCE.convertToRes(roleMenuBOList));
        }
    }
}
