package com.muci.framework.auth.api.feign;

import com.muci.framework.auth.api.entity.Role;
import com.muci.framework.auth.api.entity.User;
import com.muci.framework.auth.api.entity.UserSearch;
import com.muci.framework.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "oweikj-manage-multi-venue-auth", contextId = "userFeignClient")
public interface UserFeignClient {
    @PostMapping("user/{userId}/role")
    Result<List<Role>> findUserRoleById(@PathVariable("userId") Integer userId);

    @PostMapping("user")
    Result<User> addUser(@RequestBody User user);

    @DeleteMapping("user/{userId}")
    Result<Boolean> deleteUser(@PathVariable("userId") Integer userId);

    @PutMapping("user/{userId}")
    Result<User> updateUserById(@PathVariable("userId") Integer userId, @RequestBody User user);

    @GetMapping("user/{userId}")
    Result<User> findUserById(@PathVariable("userId") Integer userId);

    @PostMapping("user/search")
    Result<List<User>> findUsers(@RequestBody UserSearch userSearch);

    @PostMapping("user/search")
    Result findUsersPage(@RequestBody UserSearch userSearch);
}
