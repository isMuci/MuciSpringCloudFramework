package com.muci.framework.auth.application.convert;

import com.muci.framework.auth.application.dto.request.user.UserAddReq;
import com.muci.framework.auth.application.dto.request.user.UserSearchReq;
import com.muci.framework.auth.application.dto.request.user.UserUpdateReq;
import com.muci.framework.auth.application.dto.response.user.UserRes;
import com.muci.framework.auth.domain.bo.UserBO;
import com.muci.framework.auth.domain.bo.UserSearchBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserDTOConverter {
    UserDTOConverter INSTANCE = Mappers.getMapper(UserDTOConverter.class);

    UserBO convertToBO(UserAddReq userAddReq);

    UserBO convertToBO(UserUpdateReq userUpdateReq);

    UserSearchBO convertToBO(UserSearchReq userSearchReq);

    UserRes convertToRes(UserBO userBO);

    List<UserRes> convertToRes(List<UserBO> userBOList);
}
