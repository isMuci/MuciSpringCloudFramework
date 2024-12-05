package com.muci.framework.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muci.framework.auth.infra.basic.dao.MenuDao;
import com.muci.framework.auth.infra.basic.entity.Menu;
import com.muci.framework.auth.infra.basic.entity.MenuSearch;
import com.muci.framework.auth.infra.basic.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public boolean updateById(Menu menu) {
        return menuDao.updateById(menu) > 0;
    }

    @Override
    public Menu find(Menu menu) {
        return menuDao.select(menu);
    }

    @Override
    public List<Menu> finds(MenuSearch menuSearch) {
        return menuDao.selects(menuSearch);
    }

    @Override
    public IPage<Menu> finds(MenuSearch menuSearch, Page<Menu> page) {
        return menuDao.selects(menuSearch, page);
    }
}
