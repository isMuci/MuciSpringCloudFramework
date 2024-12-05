package com.muci.framework.auth.domain.convert;

import com.muci.framework.auth.domain.bo.UserRoleBO;
import com.muci.framework.auth.domain.bo.UserRoleOperateBO;
import com.muci.framework.auth.domain.bo.UserRoleSearchBO;
import com.muci.framework.auth.infra.basic.entity.UserRole;
import com.muci.framework.auth.infra.basic.entity.UserRoleOperate;
import com.muci.framework.auth.infra.basic.entity.UserRoleSearch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserRoleBOConverter {
    UserRoleBOConverter INSTANCE = Mappers.getMapper(UserRoleBOConverter.class);

    UserRoleOperate convertToEntity(UserRoleOperateBO userRoleOperateBO);

    UserRoleSearch convertToEntity(UserRoleSearchBO userRoleSearchBO);

    UserRoleBO convertToBO(UserRole userRole);

    List<UserRoleBO> convertToBO(List<UserRole> findUserRoles);
}
