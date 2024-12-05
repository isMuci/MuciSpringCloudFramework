package com.muci.framework.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muci.framework.auth.infra.basic.dao.UserDao;
import com.muci.framework.auth.infra.basic.entity.Menu;
import com.muci.framework.auth.infra.basic.entity.Role;
import com.muci.framework.auth.infra.basic.entity.User;
import com.muci.framework.auth.infra.basic.entity.UserSearch;
import com.muci.framework.auth.infra.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean updateById(User user) {
        return userDao.updateById(user) > 0;
    }

    @Override
    public User find(User user) {
        return userDao.select(user);
    }

    @Override
    public List<User> finds(UserSearch userSearch) {
        return userDao.selects(userSearch);
    }

    @Override
    public IPage<User> finds(UserSearch userSearch, Page<User> userPage) {
        return userDao.selects(userSearch, userPage);
    }

    @Override
    public List<Role> getUserRoles(Integer userId) {
        return userDao.getUserRoles(userId);
    }

    @Override
    public List<Menu> getUserPerms(Integer userId) {
        return userDao.getUserPerms(userId);
    }
}
