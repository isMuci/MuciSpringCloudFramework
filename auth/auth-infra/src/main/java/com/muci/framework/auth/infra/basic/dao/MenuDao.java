package com.muci.framework.auth.infra.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.infra.basic.entity.Menu;
import com.muci.framework.auth.infra.basic.entity.MenuSearch;
import com.muci.framework.auth.infra.basic.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao extends BaseMapper<Menu> {
    int updateById(User user);

    Menu select(Menu menu);

    List<Menu> selects(@Param("menuSearch") MenuSearch menuSearch);

    IPage<Menu> selects(@Param("menuSearch") MenuSearch menuSearch, Page<Menu> page);
}
