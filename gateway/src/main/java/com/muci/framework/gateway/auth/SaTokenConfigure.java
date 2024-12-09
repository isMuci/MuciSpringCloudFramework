package com.muci.framework.gateway.auth;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.muci.framework.auth.api.entity.Menu;
import com.muci.framework.auth.api.entity.MenuSearch;
import com.muci.framework.auth.api.feign.MenuFeignClient;
import com.muci.framework.common.entity.Result;
import com.muci.framework.gateway.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class SaTokenConfigure {

    @Autowired
    private MenuFeignClient menuFeignClient;
    @Autowired
    private RedisUtil redisUtil;

    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter().addInclude("/**").setAuth(o -> {
            List<Menu> menus = getAuthRules();
            // SaRouter.match("/**").notMatch(excludeUrls).check(r -> StpUtil.checkLogin());
            log.info("getSaReactorFilter.menus: {}", menus);
            for (Menu menu : menus) {
                SaRouter.match(SaHttpMethod.toEnum(menu.getMethod())).match(menu.getPath(),
                    r -> StpUtil.checkPermission(String.valueOf(menu.getMenuId())));
            }
        });
    }

    public List<Menu> getAuthRules() {
        List<Menu> permissionList = (List<Menu>)redisUtil.get("permission");
        if (permissionList == null) {
            Result<List<Menu>> result = menuFeignClient.findMenus(new MenuSearch());
            log.info("getAuthRules.result: {}", result);
            List<Menu> menus = new ArrayList<>();
            if (ObjectUtil.isNotNull(result.getData())) {
                menus = result.getData();
            }
            permissionList = menus;
            redisUtil.set("permission", permissionList, 60 * 60 * 24 * 30);
        }
        return permissionList;
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }
}
