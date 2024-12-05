package com.muci.framework.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muci.framework.auth.infra.basic.dao.RoleMenuDao;
import com.muci.framework.auth.infra.basic.entity.RoleMenu;
import com.muci.framework.auth.infra.basic.entity.RoleMenuOperate;
import com.muci.framework.auth.infra.basic.entity.RoleMenuSearch;
import com.muci.framework.auth.infra.basic.entity.UserRole;
import com.muci.framework.auth.infra.basic.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleMenuService")
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Boolean insertBatch(List<RoleMenu> roleMenus) {
        log.info("insertBatch.roleMenus : {}", roleMenus);
        return roleMenuDao.insertBatch(roleMenus) > 0;
    }

    @Override
    public Boolean updateBatch(RoleMenuOperate roleMenuOperate) {
        return roleMenuDao.updateBatch(roleMenuOperate) > 0;
    }

    @Override
    public List<RoleMenu> finds(RoleMenuSearch roleMenuSearch) {
        return roleMenuDao.selects(roleMenuSearch);
    }

    @Override
    public IPage<RoleMenu> finds(RoleMenuSearch roleMenuSearch, Page<UserRole> page) {
        return roleMenuDao.selects(roleMenuSearch, page);
    }
}
