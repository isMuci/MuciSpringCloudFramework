package com.muci.framework.auth.domain.service.impl;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muci.framework.auth.domain.bo.UserBO;
import com.muci.framework.auth.domain.bo.UserSearchBO;
import com.muci.framework.auth.domain.convert.UserBOConverter;
import com.muci.framework.auth.domain.service.UserDomainService;
import com.muci.framework.auth.infra.basic.entity.*;
import com.muci.framework.auth.infra.basic.service.RoleService;
import com.muci.framework.auth.infra.basic.service.UserLoginService;
import com.muci.framework.auth.infra.basic.service.UserRoleService;
import com.muci.framework.auth.infra.basic.service.UserService;
import com.muci.framework.common.context.RequestIPContext;
import com.muci.framework.common.entity.LoginUser;
import com.muci.framework.common.entity.PageInfo;
import com.muci.framework.common.entity.Result;
import com.muci.framework.common.enums.IsDel;
import com.muci.framework.common.enums.ResultCode;
import com.muci.framework.common.exception.*;
import com.muci.framework.common.util.TokenUserFiller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserLoginService userLoginService;

    @Value("${auth.create-default.user}")
    private Integer userDefaultRole;

    @Override
    @Transactional
    public UserBO addUser(UserBO userBO) {
        if (StringUtils.isBlank(userBO.getPassword()))
            userBO.setPassword("123456");
        userBO.setPassword(BCrypt.hashpw(userBO.getPassword(), BCrypt.gensalt(10)));
        TokenUserFiller.fillUserId(userBO, UserBO::setCreateBy);
        TokenUserFiller.fillUserId(userBO, UserBO::setUpdateBy);
        User user = UserBOConverter.INSTANCE.convertToEntity(userBO);
        userService.save(user);
        Boolean addRole = userRoleService
            .insertBatch(
                Collections.singletonList(UserRole.builder().userId(user.getUserId()).roleId(userDefaultRole).build()));
        Assert.isTrue(addRole, () -> new BadRequestException("添加用户角色失败"));
        User resUser = userService.find(user);
        return UserBOConverter.INSTANCE.convertToBO(resUser);
    }

    // TODO 更新逻辑

    @Override
    @Transactional
    public Boolean deleteUser(Integer userId) {
        Integer operator = TokenUserFiller.getUserId();
        int delTime = IsDel.DELETED.time();
        UserBO userBO = UserBO.builder().userId(userId).build();
        userBO.setIsDel(delTime);
        userBO.setUpdateBy(operator);
        User user = UserBOConverter.INSTANCE.convertToEntity(userBO);
        log.info("deleteUser.user : {}", JSON.toJSONString(user));
        boolean isDel = userService.updateById(user);
        log.info("deleteUser.isDel : {}", isDel);
        Assert.isTrue(isDel, () -> new BadRequestException("删除的用户不存在"));
        userRoleService.updateBatch(UserRoleOperate.builder().userIds(new HashSet<>(Collections.singleton(userId)))
            .isDel(delTime).updateBy(operator).build());
        return isDel;
    }

    @Override
    @Transactional
    public UserBO updateUser(UserBO userBO) {
        User existUser = userService.find(User.builder().userId(userBO.getUserId()).build());
        Assert.notNull(existUser, () -> new BadRequestException("更新的用户不存在"));
        log.info("updateUser.existUser : {}", JSON.toJSONString(existUser));
        if (StringUtils.isNotBlank(userBO.getPassword()))
            if (!BCrypt.checkpw(userBO.getPassword(), existUser.getPassword()))
                userBO.setPassword(BCrypt.hashpw(userBO.getPassword(), BCrypt.gensalt(10)));
            else
                throw new RepeatPasswordException();
        TokenUserFiller.fillUserId(userBO, UserBO::setUpdateBy);
        User user = UserBOConverter.INSTANCE.convertToEntity(userBO);
        userService.updateById(user);
        User resUser = userService.find(user);
        return UserBOConverter.INSTANCE.convertToBO(resUser);
    }

    @Override
    public UserBO findUser(Integer userId) {
        User user = User.builder().userId(userId).build();
        User findUser = userService.find(user);
        Assert.notNull(findUser, () -> new NotFoundException("不存在的用户"));
        return UserBOConverter.INSTANCE.convertToBO(findUser);
    }

    @Override
    public List<UserBO> findUsers(UserSearchBO userSearchBO) {
        UserSearch userSearch = UserBOConverter.INSTANCE.convertToEntity(userSearchBO);
        log.info("findUsers.userSearch : {}", JSON.toJSONString(userSearch));
        List<User> findUsers = userService.finds(userSearch);
        log.info("findUsers.findUsers:{}", JSON.toJSONString(findUsers));
        return UserBOConverter.INSTANCE.convertToBO(findUsers);
    }

    @Override
    public IPage<UserBO> findUsers(UserSearchBO userSearchBO, PageInfo pageInfo) {
        UserSearch userSearch = UserBOConverter.INSTANCE.convertToEntity(userSearchBO);
        Page<User> page = new Page<>(pageInfo.getPage(), pageInfo.getLimit());
        log.info("findUsers.userSearch : {}", JSON.toJSONString(userSearch));
        IPage<User> findUsers = userService.finds(userSearch, page);
        log.info("findUsers.findUsers:{}", JSON.toJSONString(findUsers));
        return findUsers.convert(UserBOConverter.INSTANCE::convertToBO);
    }

    @Override
    public LoginUser doLogin(LoginUser loginBO) {
        User user = userService.find(User.builder().username(loginBO.getUsername()).build());
        log.info("doLogin.user : {}", JSON.toJSONString(user));
        Assert.notNull(user, () -> new SaTokenException("用户名或密码错误"));
        if (user.getStatus() == 1)
            throw new UnauthorizedException("用户已被封禁");
        if (ObjectUtil.isNotNull(user) && BCrypt.checkpw(loginBO.getPassword(), user.getPassword())) {
            user.setPassword(null);
            List<String> roles =
                userService.getUserRoles(user.getUserId()).stream().map(Role::getRoleKey).collect(Collectors.toList());
            LoginUser resLogin = UserBOConverter.INSTANCE.convertToLogin(user);
            UserLogin userLogin = UserLogin.builder().userId(user.getUserId()).loginMac(RequestIPContext.getContext()).build();
            userLoginService.save(userLogin);
            resLogin.setRoles(roles);
            List<String> perms =
                userService.getUserPerms(user.getUserId()).stream().map(Menu::getPerms).collect(Collectors.toList());
            resLogin.setPerms(perms);
            StpUtil.login(user.getUserId(), SaLoginConfig.setExtra("loginUser", resLogin));
            resLogin.setToken(StpUtil.getTokenValue());
            log.info("doLogin.resLogin : {}", JSON.toJSONString(resLogin));
            return resLogin;
        }
        throw new SaTokenException("用户名或密码错误");
    }

    @Override
    public Boolean isLogin() {
        return StpUtil.isLogin();
    }

    @Override
    public List<Role> findUserRole(Integer userId) {
        log.info("findUserRole.userId : {}", userId);
        List<Integer> roleIds = userRoleService
            .finds(UserRoleSearch.builder().userIds(new HashSet<>(Collections.singleton(userId))).build()).stream()
            .map(UserRole::getRoleId).toList();
        log.info("findUserRole.roleIds : {}", roleIds);
        Assert.notEmpty(roleIds, () -> new ForbiddenException("用户未绑定角色"));
        return roleService.finds(RoleSearch.builder().roleIds(roleIds).build());
    }
}
