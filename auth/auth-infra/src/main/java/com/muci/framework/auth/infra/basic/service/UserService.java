package com.muci.framework.auth.infra.basic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muci.framework.auth.infra.basic.entity.Menu;
import com.muci.framework.auth.infra.basic.entity.Role;
import com.muci.framework.auth.infra.basic.entity.User;
import com.muci.framework.auth.infra.basic.entity.UserSearch;

import java.util.List;

public interface UserService extends IService<User> {

    boolean updateById(User user);

    User find(User user);

    List<User> finds(UserSearch userSearch);

    IPage<User> finds(UserSearch userSearch, Page<User> userPage);

    List<Role> getUserRoles(Integer userId);

    List<Menu> getUserPerms(Integer userId);
}
