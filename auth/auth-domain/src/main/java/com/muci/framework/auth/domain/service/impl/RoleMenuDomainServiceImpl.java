package com.muci.framework.auth.domain.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.domain.bo.RoleMenuBO;
import com.muci.framework.auth.domain.bo.RoleMenuOperateBO;
import com.muci.framework.auth.domain.bo.RoleMenuSearchBO;
import com.muci.framework.auth.domain.convert.RoleMenuBOConverter;
import com.muci.framework.auth.domain.service.RoleMenuDomainService;
import com.muci.framework.auth.domain.util.RedisUtil;
import com.muci.framework.auth.infra.basic.entity.*;
import com.muci.framework.auth.infra.basic.service.MenuService;
import com.muci.framework.auth.infra.basic.service.RoleMenuService;
import com.muci.framework.auth.infra.basic.service.RoleService;
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
public class RoleMenuDomainServiceImpl implements RoleMenuDomainService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public Boolean addRoleMenu(RoleMenuOperateBO roleMenuOperateBO) {
        List<RoleMenu> roleMenus = new ArrayList<>();
        Integer operator = TokenUserFiller.getUserId();
        roleMenuOperateBO.getRoleIds().forEach(roleId -> {
            roleMenuOperateBO.getMenuIds().forEach(menuId -> {
                roleMenus.add(
                    RoleMenu.builder().roleId(roleId).menuId(menuId).updateBy(operator).createBy(operator).build());
            });
        });
        List<String> delKeys =
            roleMenuOperateBO.getRoleIds().stream().map(roleId -> "role2perms:" + roleId).collect(Collectors.toList());
        redisUtil.remove(delKeys);
        return roleMenuService.insertBatch(roleMenus);
    }

    @Override
    @Transactional
    public Boolean deleteRoleMenu(RoleMenuOperateBO roleMenuOperateBO) {
        List<Integer> findRoles =
            roleService.finds(RoleSearch.builder().roleIds(new ArrayList<>(roleMenuOperateBO.getRoleIds())).build())
                .stream().map(Role::getRoleId).toList();
        List<Integer> findMenus =
            menuService.finds(MenuSearch.builder().menuIds(new ArrayList<>(roleMenuOperateBO.getMenuIds())).build())
                .stream().map(Menu::getMenuId).toList();
        Assert.isTrue(findRoles.size() == roleMenuOperateBO.getRoleIds().size()
            && findMenus.size() == roleMenuOperateBO.getMenuIds().size(), "删除的角色或菜单不存在");
        List<String> delKeys =
            roleMenuService.finds(RoleMenuSearch.builder().menuIds(roleMenuOperateBO.getMenuIds()).build()).stream()
                .map(RoleMenu::getRoleId).map(roleId -> "role2perms:" + roleId).collect(Collectors.toList());
        redisUtil.remove(delKeys);
        roleMenuOperateBO.setIsDel(IsDel.DELETED.time());
        TokenUserFiller.fillUserId(roleMenuOperateBO, RoleMenuOperateBO::setUpdateBy);
        RoleMenuOperate roleMenuOperate = RoleMenuBOConverter.INSTANCE.convertToEntity(roleMenuOperateBO);
        return roleMenuService.updateBatch(roleMenuOperate);
    }

    @Override
    public List<RoleMenuBO> findRoleMenus(RoleMenuSearchBO roleMenuSearchBO) {
        RoleMenuSearch roleMenuSearch = RoleMenuBOConverter.INSTANCE.convertToEntity(roleMenuSearchBO);
        List<RoleMenu> findRoleMenus = roleMenuService.finds(roleMenuSearch);
        return RoleMenuBOConverter.INSTANCE.convertToBO(findRoleMenus);
    }

    @Override
    public IPage<RoleMenuBO> findRoleMenus(RoleMenuSearchBO roleMenuSearchBO, PageInfo pageInfo) {
        RoleMenuSearch roleMenuSearch = RoleMenuBOConverter.INSTANCE.convertToEntity(roleMenuSearchBO);
        Page<UserRole> page = new Page<>(pageInfo.getPage(), pageInfo.getLimit());
        IPage<RoleMenu> findRoleMenus = roleMenuService.finds(roleMenuSearch, page);
        return findRoleMenus.convert(RoleMenuBOConverter.INSTANCE::convertToBo);
    }
}
