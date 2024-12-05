package com.muci.framework.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muci.framework.auth.infra.basic.dao.RoleDao;
import com.muci.framework.auth.infra.basic.entity.Role;
import com.muci.framework.auth.infra.basic.entity.RoleSearch;
import com.muci.framework.auth.infra.basic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public boolean updateById(Role role) {
        return roleDao.updateById(role) > 0;
    }

    @Override
    public Role find(Role role) {
        return roleDao.select(role);
    }

    @Override
    public List<Role> finds(RoleSearch roleSearch) {
        return roleDao.selects(roleSearch);
    }

    @Override
    public IPage<Role> finds(RoleSearch roleSearch, Page<Role> page) {
        return roleDao.selects(roleSearch, page);
    }
}
