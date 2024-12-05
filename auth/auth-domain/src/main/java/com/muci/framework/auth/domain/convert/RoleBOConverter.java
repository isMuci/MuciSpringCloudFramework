package com.muci.framework.auth.domain.convert;

import com.muci.framework.auth.domain.bo.RoleBO;
import com.muci.framework.auth.domain.bo.RoleSearchBO;
import com.muci.framework.auth.infra.basic.entity.Role;
import com.muci.framework.auth.infra.basic.entity.RoleSearch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleBOConverter {
    RoleBOConverter INSTANCE = Mappers.getMapper(RoleBOConverter.class);

    Role convertToEntity(RoleBO roleBO);

    RoleSearch convertToEntity(RoleSearchBO roleSearchBO);

    RoleBO convertToBO(Role role);

    List<RoleBO> convertToBO(List<Role> roleList);
}
