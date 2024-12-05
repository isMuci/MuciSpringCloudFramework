package com.muci.framework.auth.application.convert;

import com.muci.framework.auth.application.dto.request.auth.LoginReq;
import com.muci.framework.auth.application.dto.response.auth.LoginRes;
import com.muci.framework.common.entity.LoginUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConverter {
    AuthConverter INSTANCE = Mappers.getMapper(AuthConverter.class);

    LoginUser convertToBO(LoginReq loginReq);

    LoginRes convertToRes(LoginUser loginBO);
}
