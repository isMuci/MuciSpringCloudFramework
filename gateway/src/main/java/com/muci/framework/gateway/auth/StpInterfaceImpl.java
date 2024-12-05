package com.muci.framework.gateway.auth;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.ObjectUtil;
import com.muci.framework.auth.api.entity.Role;
import com.muci.framework.auth.api.feign.RoleFeignClient;
import com.muci.framework.auth.api.feign.UserFeignClient;
import com.muci.framework.common.entity.Result;
import com.muci.framework.gateway.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RoleFeignClient roleFeignClient;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<String> getPermissionList(Object username, String loginType) {
        List<String> list = new ArrayList<>();
        for (String roleId : getRoleList(username, loginType)) {
            List<String> permissionList = (List<String>)SaManager.getSaTokenDao().getObject("role2perms:" + roleId);
            if (permissionList == null) {
                Result<List<Integer>> res = roleFeignClient.findRoleMenuById(Integer.valueOf(roleId));
                List<Integer> menuIds = new ArrayList<>();
                if (ObjectUtil.isNotNull(res.getData()))
                    menuIds = res.getData();
                permissionList = menuIds.stream().map(String::valueOf).collect(Collectors.toList());
                SaManager.getSaTokenDao().setObject("role2perms:" + roleId, permissionList, 60 * 60 * 24 * 30);
            }
            list.addAll(permissionList);
        }
        return list;
    }

    @Override
    public List<String> getRoleList(Object userId, String loginType) {
        List<String> roleList = (List<String>)redisUtil.get("user2role:" + userId);
        if (roleList == null) {
            Result<List<Role>> res = userFeignClient.findUserRoleById(Integer.valueOf(userId.toString()));
            List<Integer> roleIds = new ArrayList<>();
            if (ObjectUtil.isNotNull(res.getData()))
                roleIds = res.getData().stream().map(Role::getRoleId).toList();
            roleList = roleIds.stream().map(String::valueOf).collect(Collectors.toList());
            redisUtil.set("user2role:" + userId, roleList, 60 * 60 * 24 * 30);
        }
        return roleList;
    }

}
