package com.muci.framework.auth.domain.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.domain.bo.MenuBO;
import com.muci.framework.auth.domain.bo.MenuSearchBO;
import com.muci.framework.auth.domain.convert.MenuBOConverter;
import com.muci.framework.auth.domain.service.MenuDomainService;
import com.muci.framework.auth.domain.util.RedisUtil;
import com.muci.framework.auth.infra.basic.entity.*;
import com.muci.framework.auth.infra.basic.service.MenuService;
import com.muci.framework.auth.infra.basic.service.RoleMenuService;
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
public class MenuDomainServiceImpl implements MenuDomainService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public MenuBO addMenu(MenuBO menuBO) {
        TokenUserFiller.fillUserId(menuBO, MenuBO::setCreateBy);
        TokenUserFiller.fillUserId(menuBO, MenuBO::setUpdateBy);
        Menu menu = MenuBOConverter.INSTANCE.convertToEntity(menuBO);
        menuService.save(menu);
        Menu resMenu = menuService.find(Menu.builder().menuId(menu.getMenuId()).build());
        return MenuBOConverter.INSTANCE.convertToBO(resMenu);
    }

    @Override
    @Transactional
    public Boolean deleteMenu(Integer menuId) {
        Menu menu = menuService.find(Menu.builder().menuId(menuId).build());
        Assert.notNull(menu, () -> new BadRequestException("删除的菜单不存在"));
        HashSet<Integer> delMenuId = new HashSet<>(Collections.singleton(menuId));
        List<String> delKeys = roleMenuService.finds(RoleMenuSearch.builder().menuIds(delMenuId).build()).stream()
            .map(RoleMenu::getMenuId).map(roleId -> "role2perms:" + roleId).collect(Collectors.toList());
        redisUtil.remove(delKeys);
        int delTime = IsDel.DELETED.time();
        Integer operator = TokenUserFiller.getUserId();
        RoleMenuOperate roleMenu =
            RoleMenuOperate.builder().menuIds(delMenuId).updateBy(operator).isDel(delTime).build();
        roleMenuService.updateBatch(roleMenu);
        Menu delMenu = Menu.builder().menuId(menuId).updateBy(operator).isDel(delTime).build();
        return menuService.updateById(delMenu);
    }

    @Override
    @Transactional
    public MenuBO updateMenu(MenuBO menuBO) {
        Menu menu = menuService.find(Menu.builder().menuId(menuBO.getMenuId()).build());
        Assert.notNull(menu, () -> new BadRequestException("更新的菜单不存在"));
        List<String> delKeys = roleMenuService
            .finds(RoleMenuSearch.builder().menuIds(new HashSet<>(Collections.singleton(menuBO.getMenuId()))).build())
            .stream().map(RoleMenu::getMenuId).map(roleId -> "role2perms:" + roleId).collect(Collectors.toList());
        redisUtil.remove(delKeys);
        TokenUserFiller.fillUserId(menuBO, MenuBO::setUpdateBy);
        Menu updateMenu = MenuBOConverter.INSTANCE.convertToEntity(menuBO);
        menuService.updateById(updateMenu);
        Menu resMenu = menuService.find(Menu.builder().menuId(menuBO.getMenuId()).build());
        return MenuBOConverter.INSTANCE.convertToBO(resMenu);
    }

    @Override
    public MenuBO findMenu(Integer menuId) {
        Menu menu = Menu.builder().menuId(menuId).build();
        Menu findMenu = menuService.find(menu);
        Assert.notNull(findMenu, () -> new NotFoundException("不存在的菜单"));
        return MenuBOConverter.INSTANCE.convertToBO(findMenu);
    }

    @Override
    public List<MenuBO> findMenus(MenuSearchBO menuSearchBO) {
        MenuSearch menuSearch = MenuBOConverter.INSTANCE.convertToEntity(menuSearchBO);
        log.info("findMenus.menuSearch : {}", JSON.toJSONString(menuSearch));
        List<Menu> findMenus = menuService.finds(menuSearch);
        log.info("findMenus.findMenus:{}", JSON.toJSONString(findMenus));
        return MenuBOConverter.INSTANCE.convertToBO(findMenus);
    }

    @Override
    public IPage<MenuBO> findMenus(MenuSearchBO menuSearchBO, PageInfo pageInfo) {
        MenuSearch menuSearch = MenuBOConverter.INSTANCE.convertToEntity(menuSearchBO);
        Page<Menu> page = new Page<>(pageInfo.getPage(), pageInfo.getLimit());
        log.info("findMenus.menuSearch : {}", JSON.toJSONString(menuSearch));
        IPage<Menu> findMenus = menuService.finds(menuSearch, page);
        log.info("findMenus.findMenus:{}", JSON.toJSONString(findMenus));
        return findMenus.convert(MenuBOConverter.INSTANCE::convertToBO);
    }
}
