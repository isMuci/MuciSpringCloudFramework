package com.muci.framework.auth.application.convert;

import com.muci.framework.auth.application.dto.request.userRole.UserRoleOperateReq;
import com.muci.framework.auth.application.dto.request.userRole.UserRoleSearchReq;
import com.muci.framework.auth.application.dto.response.userRole.UserRoleRes;
import com.muci.framework.auth.domain.bo.UserRoleBO;
import com.muci.framework.auth.domain.bo.UserRoleOperateBO;
import com.muci.framework.auth.domain.bo.UserRoleSearchBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserRoleDTOConverter {
    UserRoleDTOConverter INSTANCE = Mappers.getMapper(UserRoleDTOConverter.class);

    UserRoleOperateBO convertToBO(UserRoleOperateReq userRoleOperateReq);

    UserRoleSearchBO convertToBO(UserRoleSearchReq userRoleSearchReq);

    UserRoleRes convertToRes(UserRoleBO userRoleBO);

    List<UserRoleRes> convertToRes(List<UserRoleBO> userRoleBOList);
}
