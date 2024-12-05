package com.muci.framework.auth.application.convert;

import com.muci.framework.auth.application.dto.request.role.RoleAddReq;
import com.muci.framework.auth.application.dto.request.role.RoleSearchReq;
import com.muci.framework.auth.application.dto.request.role.RoleUpdateReq;
import com.muci.framework.auth.application.dto.response.role.RoleRes;
import com.muci.framework.auth.domain.bo.RoleBO;
import com.muci.framework.auth.domain.bo.RoleSearchBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleDTOConverter {
    RoleDTOConverter INSTANCE = Mappers.getMapper(RoleDTOConverter.class);

    RoleBO convertToBO(RoleAddReq roleAddReq);

    RoleBO convertToBO(RoleUpdateReq roleUpdateReq);

    RoleSearchBO convertToBO(RoleSearchReq roleSearchReq);

    RoleRes convertToRes(RoleBO roleBO);

    List<RoleRes> convertToRes(List<RoleBO> roleBOList);

}
