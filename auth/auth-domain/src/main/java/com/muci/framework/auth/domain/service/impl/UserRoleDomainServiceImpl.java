package com.muci.framework.auth.domain.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.domain.bo.UserRoleBO;
import com.muci.framework.auth.domain.bo.UserRoleOperateBO;
import com.muci.framework.auth.domain.bo.UserRoleSearchBO;
import com.muci.framework.auth.domain.convert.UserRoleBOConverter;
import com.muci.framework.auth.domain.service.UserRoleDomainService;
import com.muci.framework.auth.domain.util.RedisUtil;
import com.muci.framework.auth.infra.basic.entity.*;
import com.muci.framework.auth.infra.basic.service.RoleService;
import com.muci.framework.auth.infra.basic.service.UserRoleService;
import com.muci.framework.auth.infra.basic.service.UserService;
import com.muci.framework.common.entity.PageInfo;
import com.muci.framework.common.enums.IsDel;
import com.muci.framework.common.util.TokenUserFiller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserRoleDomainServiceImpl implements UserRoleDomainService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public Boolean addUserRole(UserRoleOperateBO userRoleOperateBO) {
        List<UserRole> userRoles = new ArrayList<>();
        Integer operator = TokenUserFiller.getUserId();
        userRoleOperateBO.getUserIds().forEach(userId -> {
            userRoleOperateBO.getRoleIds().forEach(roleId -> {
                userRoles.add(
                    UserRole.builder().userId(userId).roleId(roleId).updateBy(operator).createBy(operator).build());
            });
        });
        List<String> delKeys =
            userRoleOperateBO.getUserIds().stream().map(userId -> "user2role:" + userId).collect(Collectors.toList());
        redisUtil.remove(delKeys);
        return userRoleService.insertBatch(userRoles);
    }

    @Override
    @Transactional
    public Boolean deleteUserRole(UserRoleOperateBO userRoleOperateBO) {
        List<Integer> findUsers =
            userService.finds(UserSearch.builder().userIds(new ArrayList<>(userRoleOperateBO.getUserIds())).build())
                .stream().map(User::getUserId).toList();
        List<Integer> findRoles =
            roleService.finds(RoleSearch.builder().roleIds(new ArrayList<>(userRoleOperateBO.getRoleIds())).build())
                .stream().map(Role::getRoleId).toList();
        Assert.isTrue(findUsers.size() == userRoleOperateBO.getUserIds().size()
            && findRoles.size() == userRoleOperateBO.getRoleIds().size(), "删除的用户或角色不存在");
        List<String> delKeys =
            userRoleService.finds(UserRoleSearch.builder().roleIds(userRoleOperateBO.getRoleIds()).build()).stream()
                .map(UserRole::getUserId).map(userId -> "user2role:" + userId).collect(Collectors.toList());
        redisUtil.remove(delKeys);
        userRoleOperateBO.setIsDel(IsDel.DELETED.time());
        TokenUserFiller.fillUserId(userRoleOperateBO, UserRoleOperateBO::setUpdateBy);
        UserRoleOperate userRoleOperate = UserRoleBOConverter.INSTANCE.convertToEntity(userRoleOperateBO);
        return userRoleService.updateBatch(userRoleOperate);
    }

    @Override
    public List<UserRoleBO> findUserRoles(UserRoleSearchBO userRoleSearchBO) {
        UserRoleSearch userRoleSearch = UserRoleBOConverter.INSTANCE.convertToEntity(userRoleSearchBO);
        List<UserRole> findUserRoles = userRoleService.finds(userRoleSearch);
        return UserRoleBOConverter.INSTANCE.convertToBO(findUserRoles);
    }

    @Override
    public IPage<UserRoleBO> findUserRoles(UserRoleSearchBO userRoleSearchBO, PageInfo pageInfo) {
        UserRoleSearch userRoleSearch = UserRoleBOConverter.INSTANCE.convertToEntity(userRoleSearchBO);
        Page<UserRole> page = new Page<>(pageInfo.getPage(), pageInfo.getLimit());
        IPage<UserRole> findUserRoles = userRoleService.finds(userRoleSearch, page);
        return findUserRoles.convert(UserRoleBOConverter.INSTANCE::convertToBO);
    }
}
