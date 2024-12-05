package com.muci.framework.auth.domain.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.domain.bo.RoleBO;
import com.muci.framework.auth.domain.bo.RoleSearchBO;
import com.muci.framework.auth.domain.convert.RoleBOConverter;
import com.muci.framework.auth.domain.service.RoleDomainService;
import com.muci.framework.auth.domain.util.RedisUtil;
import com.muci.framework.auth.infra.basic.entity.*;
import com.muci.framework.auth.infra.basic.service.RoleMenuService;
import com.muci.framework.auth.infra.basic.service.RoleService;
import com.muci.framework.auth.infra.basic.service.UserRoleService;
import com.muci.framework.common.entity.PageInfo;
import com.muci.framework.common.enums.IsDel;
import com.muci.framework.common.exception.BadRequestException;
import com.muci.framework.common.exception.NotFoundException;
import com.muci.framework.common.util.TokenUserFiller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleDomainServiceImpl implements RoleDomainService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public RoleBO addRole(RoleBO roleBO) {
        TokenUserFiller.fillUserId(roleBO, RoleBO::setCreateBy);
        TokenUserFiller.fillUserId(roleBO, RoleBO::setUpdateBy);
        Role role = RoleBOConverter.INSTANCE.convertToEntity(roleBO);
        roleService.save(role);
        log.info("addRole.role:{}", JSON.toJSONString(role));
        Role resRole = roleService.find(Role.builder().roleId(role.getRoleId()).build());
        return RoleBOConverter.INSTANCE.convertToBO(resRole);
    }

    @Override
    @Transactional
    public Boolean deleteRole(Integer roleId) {
        Role role = roleService.find(Role.builder().roleId(roleId).build());
        Assert.notNull(role, () -> new BadRequestException("删除的角色不存在"));
        HashSet<Integer> delRoleId = new HashSet<>(Collections.singleton(roleId));
        List<String> delKeys = userRoleService.finds(UserRoleSearch.builder().roleIds(delRoleId).build()).stream()
            .map(UserRole::getUserId).map(userId -> "user2role:" + userId).collect(Collectors.toList());
        redisUtil.remove(delKeys);
        int delTime = IsDel.DELETED.time();
        Integer operator = TokenUserFiller.getUserId();
        UserRoleOperate userRole =
            UserRoleOperate.builder().roleIds(delRoleId).updateBy(operator).isDel(delTime).build();
        userRoleService.updateBatch(userRole);
        Role delRole = Role.builder().roleId(roleId).updateBy(operator).isDel(delTime).build();
        return roleService.updateById(delRole);
    }

    @Override
    @Transactional
    public RoleBO updateRole(RoleBO roleBO) {
        Role role = roleService.find(Role.builder().roleId(roleBO.getRoleId()).build());
        Assert.notNull(role, () -> new BadRequestException("更新的角色不存在"));
        List<String> delKeys = userRoleService
            .finds(UserRoleSearch.builder().roleIds(new HashSet<>(Collections.singleton(roleBO.getRoleId()))).build())
            .stream().map(UserRole::getUserId).map(userId -> "user2role:" + userId).collect(Collectors.toList());
        redisUtil.remove(delKeys);
        TokenUserFiller.fillUserId(roleBO, RoleBO::setUpdateBy);
        Role updateRole = RoleBOConverter.INSTANCE.convertToEntity(roleBO);
        roleService.updateById(updateRole);
        Role resRole = roleService.find(Role.builder().roleId(roleBO.getRoleId()).build());
        return RoleBOConverter.INSTANCE.convertToBO(resRole);
    }

    @Override
    public RoleBO findRole(Integer roleId) {
        Role role = Role.builder().roleId(roleId).build();
        Role findRole = roleService.find(role);
        Assert.notNull(findRole, () -> new NotFoundException("不存在的角色"));
        return RoleBOConverter.INSTANCE.convertToBO(findRole);
    }

    @Override
    public List<RoleBO> findRoles(RoleSearchBO roleSearchBO) {
        RoleSearch roleSearch = RoleBOConverter.INSTANCE.convertToEntity(roleSearchBO);
        log.info("findRoles.roleSearch : {}", JSON.toJSONString(roleSearch));
        List<Role> findRoles = roleService.finds(roleSearch);
        log.info("findRoles.findRoles:{}", JSON.toJSONString(findRoles));
        return RoleBOConverter.INSTANCE.convertToBO(findRoles);
    }

    @Override
    public IPage<RoleBO> findRoles(RoleSearchBO roleSearchBO, PageInfo pageInfo) {
        RoleSearch roleSearch = RoleBOConverter.INSTANCE.convertToEntity(roleSearchBO);
        Page<Role> page = new Page<>(pageInfo.getPage(), pageInfo.getLimit());
        log.info("findRoles.roleSearch : {}", JSON.toJSONString(roleSearch));
        IPage<Role> findRoles = roleService.finds(roleSearch, page);
        log.info("findRoles.findRoles:{}", JSON.toJSONString(findRoles));
        return findRoles.convert(RoleBOConverter.INSTANCE::convertToBO);
    }

    @Override
    public List<Integer> findRoleMenu(Integer roleId) {
        List<RoleMenu> roleMenus = roleMenuService
            .finds(RoleMenuSearch.builder().roleIds(new HashSet<>(Collections.singleton(roleId))).build());
        return roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }
}
