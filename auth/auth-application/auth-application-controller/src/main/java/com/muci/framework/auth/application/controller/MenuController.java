package com.muci.framework.auth.application.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muci.framework.auth.application.convert.MenuDTOConverter;
import com.muci.framework.auth.application.dto.request.menu.MenuAddReq;
import com.muci.framework.auth.application.dto.request.menu.MenuSearchReq;
import com.muci.framework.auth.application.dto.request.menu.MenuUpdateReq;
import com.muci.framework.auth.domain.bo.MenuBO;
import com.muci.framework.auth.domain.bo.MenuSearchBO;
import com.muci.framework.auth.domain.service.MenuDomainService;
import com.muci.framework.common.convert.PageInfoConverter;
import com.muci.framework.common.entity.PageInfo;
import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuDomainService menuDomainService;

    @PostMapping
    public Result addMenu(@RequestBody MenuAddReq menuAddReq) {
        try {
            log.info("addMenu.menuAddReq : {}", JSON.toJSONString(menuAddReq));
            MenuBO menuBO = MenuDTOConverter.INSTANCE.convertToBO(menuAddReq);
            MenuBO resMenuBO = menuDomainService.addMenu(menuBO);
            return Result.success(MenuDTOConverter.INSTANCE.convertToRes(resMenuBO));
        } catch (DuplicateKeyException e) {
            log.error("addMenu.DuplicateKeyException : ", e);
            return Result.fail(ResultCode.CONFLICT, "请勿重复添加同名菜单");
        }
    }

    @DeleteMapping("{menuId}")
    public Result deleteMenu(@PathVariable("menuId") Integer menuId) {
        log.info("deleteMenu.menuId : {}", menuId);
        return Result.success(menuDomainService.deleteMenu(menuId));
    }

    @PutMapping("{menuId}")
    public Result updateMenu(@PathVariable("menuId") Integer menuId, @RequestBody MenuUpdateReq menuUpdateReq) {
        try {
            log.info("updateMenu.menuId : {}", menuId);
            log.info("updateMenu.menuUpdateReq : {}", JSON.toJSONString(menuUpdateReq));
            MenuBO menuBO = MenuDTOConverter.INSTANCE.convertToBO(menuUpdateReq);
            menuBO.setMenuId(menuId);
            MenuBO resMenu = menuDomainService.updateMenu(menuBO);
            return Result.success(MenuDTOConverter.INSTANCE.convertToRes(resMenu));
        } catch (DuplicateKeyException e) {
            log.error("updateMenu.DuplicateKeyException : {}", e.getMessage());
            return Result.fail(ResultCode.BAD_REQUEST, "关键信息重复");
        }
    }

    @GetMapping("{menuId}")
    public Result findMenuById(@PathVariable("menuId") Integer menuId) {
        log.info("findOneMenu.menuId : {}", menuId);
        MenuBO menu = menuDomainService.findMenu(menuId);
        return Result.success(MenuDTOConverter.INSTANCE.convertToRes(menu));
    }

    @PostMapping("search")
    public Result findMenus(@RequestBody MenuSearchReq menuSearchReq) {
        MenuSearchBO menuSearchBO = MenuDTOConverter.INSTANCE.convertToBO(menuSearchReq);
        PageInfo page = PageInfoConverter.INSTANCE.convert(menuSearchReq);
        log.info("findMenus.menuSearchBO : {}", JSON.toJSONString(menuSearchBO));
        log.info("findMenus.page : {}", JSON.toJSONString(page));
        if (page.limited()) {
            IPage<MenuBO> menuBOList = menuDomainService.findMenus(menuSearchBO, page);
            return Result.success(menuBOList.convert(MenuDTOConverter.INSTANCE::convertToRes));
        } else {
            List<MenuBO> menuBOList = menuDomainService.findMenus(menuSearchBO);
            return Result.success(MenuDTOConverter.INSTANCE.convertToRes(menuBOList));
        }
    }
}
