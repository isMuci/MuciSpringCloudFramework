package com.muci.framework.auth.domain.convert;

import com.muci.framework.auth.domain.bo.RoleMenuBO;
import com.muci.framework.auth.domain.bo.RoleMenuOperateBO;
import com.muci.framework.auth.domain.bo.RoleMenuSearchBO;
import com.muci.framework.auth.infra.basic.entity.RoleMenu;
import com.muci.framework.auth.infra.basic.entity.RoleMenuOperate;
import com.muci.framework.auth.infra.basic.entity.RoleMenuSearch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMenuBOConverter {
    RoleMenuBOConverter INSTANCE = Mappers.getMapper(RoleMenuBOConverter.class);

    RoleMenuOperate convertToEntity(RoleMenuOperateBO roleMenuOperateBO);

    RoleMenuSearch convertToEntity(RoleMenuSearchBO roleMenuSearchBO);

    RoleMenuBO convertToBo(RoleMenu roleMenu);

    List<RoleMenuBO> convertToBO(List<RoleMenu> findRoleMenus);
}
