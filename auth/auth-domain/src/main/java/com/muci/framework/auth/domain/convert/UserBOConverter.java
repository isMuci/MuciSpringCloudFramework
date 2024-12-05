package com.muci.framework.auth.domain.convert;

import com.muci.framework.auth.domain.bo.UserBO;
import com.muci.framework.auth.domain.bo.UserSearchBO;
import com.muci.framework.auth.infra.basic.entity.User;
import com.muci.framework.auth.infra.basic.entity.UserSearch;
import com.muci.framework.common.entity.LoginUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserBOConverter {
    UserBOConverter INSTANCE = Mappers.getMapper(UserBOConverter.class);

    User convertToEntity(UserBO userBO);

    UserSearch convertToEntity(UserSearchBO userSearchBO);

    UserBO convertToBO(User user);

    List<UserBO> convertToBO(List<User> userList);

    LoginUser convertToLogin(User user);
}
