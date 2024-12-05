package com.muci.framework.auth.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.domain.bo.UserBO;
import com.muci.framework.auth.domain.bo.UserSearchBO;
import com.muci.framework.auth.infra.basic.entity.Role;
import com.muci.framework.common.entity.LoginUser;
import com.muci.framework.common.entity.PageInfo;

import java.util.List;

public interface UserDomainService {
    UserBO addUser(UserBO userBO);

    Boolean deleteUser(Integer userId);

    UserBO updateUser(UserBO userBO);

    UserBO findUser(Integer userId);

    List<UserBO> findUsers(UserSearchBO userSearchBO);

    IPage<UserBO> findUsers(UserSearchBO userSearchBO, PageInfo pageInfo);

    LoginUser doLogin(LoginUser loginBO);

    Boolean isLogin();

    List<Role> findUserRole(Integer userId);
}
