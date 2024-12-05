package com.muci.framework.auth.api.feign;

import com.muci.framework.auth.api.entity.UserRoleOperate;
import com.muci.framework.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "oweikj-manage-multi-venue-auth", contextId = "userRoleFeignClient")
public interface UserRoleFeignClient {

    @PostMapping("userRole")
    Result<Boolean> addUserRole(@RequestBody UserRoleOperate userRoleAdd);
}
