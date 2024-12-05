package com.muci.framework.auth.application.convert;

import com.muci.framework.auth.application.dto.request.roleMenu.RoleMenuOperateReq;
import com.muci.framework.auth.application.dto.request.roleMenu.RoleMenuSearchReq;
import com.muci.framework.auth.application.dto.response.roleMenu.RoleMenuRes;
import com.muci.framework.auth.domain.bo.RoleMenuBO;
import com.muci.framework.auth.domain.bo.RoleMenuOperateBO;
import com.muci.framework.auth.domain.bo.RoleMenuSearchBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMenuDTOConverter {
    RoleMenuDTOConverter INSTANCE = Mappers.getMapper(RoleMenuDTOConverter.class);

    RoleMenuOperateBO convertToBO(RoleMenuOperateReq roleMenuOperateReq);

    RoleMenuSearchBO convertToBO(RoleMenuSearchReq roleMenuSearchReq);

    RoleMenuRes convertToRes(RoleMenuBO roleMenuBO);

    List<RoleMenuRes> convertToRes(List<RoleMenuBO> roleMenuBOS);
}
