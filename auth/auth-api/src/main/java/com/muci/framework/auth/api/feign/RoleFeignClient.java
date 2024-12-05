package com.muci.framework.auth.api.feign;

import com.muci.framework.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "oweikj-manage-multi-venue-auth", contextId = "roleFeignClient")
public interface RoleFeignClient {
    @PostMapping("/role/{roleId}/menu")
    Result<List<Integer>> findRoleMenuById(@PathVariable("roleId") Integer roleId);
}
