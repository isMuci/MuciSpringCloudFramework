package com.muci.framework.auth.infra.basic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muci.framework.auth.infra.basic.entity.UserRole;
import com.muci.framework.auth.infra.basic.entity.UserRoleOperate;
import com.muci.framework.auth.infra.basic.entity.UserRoleSearch;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {
    Boolean insertBatch(List<UserRole> userRoles);

    Boolean updateBatch(UserRoleOperate userRoleOperate);

    List<UserRole> finds(UserRoleSearch userRoleSearch);

    IPage<UserRole> finds(UserRoleSearch userRoleSearch, Page<UserRole> page);
}
