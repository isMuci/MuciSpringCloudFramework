package com.muci.framework.auth.infra.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.infra.basic.entity.UserRole;
import com.muci.framework.auth.infra.basic.entity.UserRoleOperate;
import com.muci.framework.auth.infra.basic.entity.UserRoleSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleDao extends BaseMapper<UserRole> {

    int insertBatch(@Param("userRoles") List<UserRole> userRoles);

    int updateBatch(@Param("userRoleOperate") UserRoleOperate userRoleOperate);

    List<UserRole> selects(@Param("userRoleSearch") UserRoleSearch userRoleSearch);

    IPage<UserRole> selects(@Param("userRoleSearch") UserRoleSearch userRoleSearch, Page<UserRole> page);
}
