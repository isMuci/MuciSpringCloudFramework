package com.muci.framework.auth.infra.basic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muci.framework.auth.infra.basic.entity.Role;
import com.muci.framework.auth.infra.basic.entity.RoleSearch;

import java.util.List;

public interface RoleService extends IService<Role> {
    boolean updateById(Role role);

    Role find(Role role);

    List<Role> finds(RoleSearch roleSearch);

    IPage<Role> finds(RoleSearch roleSearch, Page<Role> page);
}
