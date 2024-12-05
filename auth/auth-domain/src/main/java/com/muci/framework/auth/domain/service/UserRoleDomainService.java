package com.muci.framework.auth.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.domain.bo.UserRoleBO;
import com.muci.framework.auth.domain.bo.UserRoleOperateBO;
import com.muci.framework.auth.domain.bo.UserRoleSearchBO;
import com.muci.framework.common.entity.PageInfo;

import java.util.List;

public interface UserRoleDomainService {
    Boolean addUserRole(UserRoleOperateBO userRoleOperateBO);

    Boolean deleteUserRole(UserRoleOperateBO userRoleOperateBO);

    List<UserRoleBO> findUserRoles(UserRoleSearchBO userRoleSearchBO);

    IPage<UserRoleBO> findUserRoles(UserRoleSearchBO userRoleSearchBO, PageInfo pageInfo);
}
