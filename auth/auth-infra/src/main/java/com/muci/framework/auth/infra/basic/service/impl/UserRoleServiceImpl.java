package com.muci.framework.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muci.framework.auth.infra.basic.dao.UserRoleDao;
import com.muci.framework.auth.infra.basic.entity.UserRole;
import com.muci.framework.auth.infra.basic.entity.UserRoleOperate;
import com.muci.framework.auth.infra.basic.entity.UserRoleSearch;
import com.muci.framework.auth.infra.basic.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public Boolean insertBatch(List<UserRole> userRoles) {
        return userRoleDao.insertBatch(userRoles) > 0;
    }

    @Override
    public Boolean updateBatch(UserRoleOperate userRoleOperate) {
        return userRoleDao.updateBatch(userRoleOperate) > 0;
    }

    @Override
    public List<UserRole> finds(UserRoleSearch userRoleSearch) {
        return userRoleDao.selects(userRoleSearch);
    }

    @Override
    public IPage<UserRole> finds(UserRoleSearch userRoleSearch, Page<UserRole> page) {
        return userRoleDao.selects(userRoleSearch, page);
    }
}
