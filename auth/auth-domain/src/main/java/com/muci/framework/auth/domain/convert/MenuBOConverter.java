package com.muci.framework.auth.domain.convert;

import com.muci.framework.auth.domain.bo.MenuBO;
import com.muci.framework.auth.domain.bo.MenuSearchBO;
import com.muci.framework.auth.infra.basic.entity.Menu;
import com.muci.framework.auth.infra.basic.entity.MenuSearch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuBOConverter {
    MenuBOConverter INSTANCE = Mappers.getMapper(MenuBOConverter.class);

    Menu convertToEntity(MenuBO menuBO);

    MenuSearch convertToEntity(MenuSearchBO menuSearchBO);

    MenuBO convertToBO(Menu menu);

    List<MenuBO> convertToBO(List<Menu> menus);
}
