package com.muci.framework.auth.infra.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.infra.basic.entity.RoleMenu;
import com.muci.framework.auth.infra.basic.entity.RoleMenuOperate;
import com.muci.framework.auth.infra.basic.entity.RoleMenuSearch;
import com.muci.framework.auth.infra.basic.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuDao extends BaseMapper<RoleMenu> {
    int insertBatch(@Param("roleMenus") List<RoleMenu> roleMenus);

    int updateBatch(@Param("roleMenuOperate") RoleMenuOperate roleMenuOperate);

    List<RoleMenu> selects(@Param("roleMenuSearch") RoleMenuSearch roleMenuSearch);

    IPage<RoleMenu> selects(@Param("roleMenuSearch") RoleMenuSearch roleMenuSearch, Page<UserRole> page);
}
