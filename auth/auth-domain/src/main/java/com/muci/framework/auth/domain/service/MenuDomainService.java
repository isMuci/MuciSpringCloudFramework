package com.muci.framework.auth.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.domain.bo.MenuBO;
import com.muci.framework.auth.domain.bo.MenuSearchBO;
import com.muci.framework.common.entity.PageInfo;

import java.util.List;

public interface MenuDomainService {
    MenuBO addMenu(MenuBO menuBO);

    Boolean deleteMenu(Integer menuId);

    MenuBO updateMenu(MenuBO menuBO);

    MenuBO findMenu(Integer menuId);

    List<MenuBO> findMenus(MenuSearchBO menuSearchBO);

    IPage<MenuBO> findMenus(MenuSearchBO menuSearchBO, PageInfo pageInfo);
}
