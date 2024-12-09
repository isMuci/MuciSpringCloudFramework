package com.muci.framework.auth.api.feign;

import com.muci.framework.auth.api.entity.UserRoleOperate;
import com.muci.framework.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth", contextId = "userRoleFeignClient")
public interface UserRoleFeignClient {

    @PostMapping("userRole")
    Result<Boolean> addUserRole(@RequestBody UserRoleOperate userRoleAdd);
}
