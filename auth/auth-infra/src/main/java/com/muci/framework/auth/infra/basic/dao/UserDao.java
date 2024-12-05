package com.muci.framework.auth.infra.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.infra.basic.entity.Menu;
import com.muci.framework.auth.infra.basic.entity.Role;
import com.muci.framework.auth.infra.basic.entity.User;
import com.muci.framework.auth.infra.basic.entity.UserSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper<User> {
    int updateById(User user);

    User select(User user);

    List<User> selects(@Param("userSearch") UserSearch userSearch);

    IPage<User> selects(@Param("userSearch") UserSearch userSearch, Page<User> page);

    List<Role> getUserRoles(Integer userId);

    List<Menu> getUserPerms(Integer userId);
}
