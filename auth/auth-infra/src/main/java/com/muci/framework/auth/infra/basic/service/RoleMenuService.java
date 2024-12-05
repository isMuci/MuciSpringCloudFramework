package com.muci.framework.auth.infra.basic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muci.framework.auth.infra.basic.entity.RoleMenu;
import com.muci.framework.auth.infra.basic.entity.RoleMenuOperate;
import com.muci.framework.auth.infra.basic.entity.RoleMenuSearch;
import com.muci.framework.auth.infra.basic.entity.UserRole;

import java.util.List;

public interface RoleMenuService extends IService<RoleMenu> {
    Boolean insertBatch(List<RoleMenu> roleMenus);

    Boolean updateBatch(RoleMenuOperate roleMenuOperate);

    List<RoleMenu> finds(RoleMenuSearch roleMenuSearch);

    IPage<RoleMenu> finds(RoleMenuSearch roleMenuSearch, Page<UserRole> page);
}
