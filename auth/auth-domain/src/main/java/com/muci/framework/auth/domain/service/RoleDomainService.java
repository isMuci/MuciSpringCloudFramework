package com.muci.framework.auth.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.domain.bo.RoleBO;
import com.muci.framework.auth.domain.bo.RoleSearchBO;
import com.muci.framework.common.entity.PageInfo;

import java.util.List;

public interface RoleDomainService {
    RoleBO addRole(RoleBO roleBO);

    Boolean deleteRole(Integer roleId);

    RoleBO updateRole(RoleBO roleBO);

    RoleBO findRole(Integer roleId);

    List<RoleBO> findRoles(RoleSearchBO roleSearchBO);

    IPage<RoleBO> findRoles(RoleSearchBO roleSearchBO, PageInfo pageInfo);

    List<Integer> findRoleMenu(Integer roleId);
}
