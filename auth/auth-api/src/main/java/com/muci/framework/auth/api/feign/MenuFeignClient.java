package com.muci.framework.auth.api.feign;

import com.muci.framework.auth.api.entity.Menu;
import com.muci.framework.auth.api.entity.MenuSearch;
import com.muci.framework.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "oweikj-manage-multi-venue-auth", contextId = "menuFeignClient")
public interface MenuFeignClient {
    @PostMapping("menu/search")
    Result<List<Menu>> findMenus(@RequestBody MenuSearch menuSearch);
}