package com.muci.framework.auth.infra.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.infra.basic.entity.Role;
import com.muci.framework.auth.infra.basic.entity.RoleSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao extends BaseMapper<Role> {
    int updateById(Role role);

    Role select(Role role);

    List<Role> selects(@Param("roleSearch") RoleSearch roleSearch);

    IPage<Role> selects(@Param("roleSearch") RoleSearch roleSearch, Page<Role> page);
}
