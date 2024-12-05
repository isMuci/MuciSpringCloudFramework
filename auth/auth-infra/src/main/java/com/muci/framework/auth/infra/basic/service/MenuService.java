package com.muci.framework.auth.infra.basic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muci.framework.auth.infra.basic.entity.Menu;
import com.muci.framework.auth.infra.basic.entity.MenuSearch;

import java.util.List;

public interface MenuService extends IService<Menu> {
    boolean updateById(Menu menu);

    Menu find(Menu menu);

    List<Menu> finds(MenuSearch menuSearch);

    IPage<Menu> finds(MenuSearch menuSearch, Page<Menu> page);
}
