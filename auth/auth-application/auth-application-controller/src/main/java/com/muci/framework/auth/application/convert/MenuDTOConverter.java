package com.muci.framework.auth.application.convert;

import com.muci.framework.auth.application.dto.request.menu.MenuAddReq;
import com.muci.framework.auth.application.dto.request.menu.MenuSearchReq;
import com.muci.framework.auth.application.dto.request.menu.MenuUpdateReq;
import com.muci.framework.auth.application.dto.response.menu.MenuRes;
import com.muci.framework.auth.domain.bo.MenuBO;
import com.muci.framework.auth.domain.bo.MenuSearchBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuDTOConverter {
    MenuDTOConverter INSTANCE = Mappers.getMapper(MenuDTOConverter.class);

    MenuBO convertToBO(MenuAddReq menuAddReq);

    MenuBO convertToBO(MenuUpdateReq menuUpdateReq);

    MenuSearchBO convertToBO(MenuSearchReq menuSearchReq);

    MenuRes convertToRes(MenuBO resMenuBO);

    List<MenuRes> convertToRes(List<MenuBO> menuBOList);
}
