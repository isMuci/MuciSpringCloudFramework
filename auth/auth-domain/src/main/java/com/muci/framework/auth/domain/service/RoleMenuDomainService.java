package com.muci.framework.auth.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.domain.bo.RoleMenuBO;
import com.muci.framework.auth.domain.bo.RoleMenuOperateBO;
import com.muci.framework.auth.domain.bo.RoleMenuSearchBO;
import com.muci.framework.common.entity.PageInfo;

import java.util.List;

public interface RoleMenuDomainService {
    Boolean addRoleMenu(RoleMenuOperateBO roleMenuOperateBO);

    Boolean deleteRoleMenu(RoleMenuOperateBO roleMenuOperateBO);

    List<RoleMenuBO> findRoleMenus(RoleMenuSearchBO roleMenuSearchBO);

    IPage<RoleMenuBO> findRoleMenus(RoleMenuSearchBO roleMenuSearchBO, PageInfo pageInfo);
}
