package com.muci.framework.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muci.framework.auth.infra.basic.dao.UserLoginDao;
import com.muci.framework.auth.infra.basic.entity.UserLogin;
import com.muci.framework.auth.infra.basic.service.UserLoginService;
import org.springframework.stereotype.Service;

/**
 * (UserLogin)表服务实现类
 *
 * @author makejava
 * @since 2024-10-17 14:16:45
 */
@Service("userLoginService")
public class UserLoginServiceImpl extends ServiceImpl<UserLoginDao, UserLogin> implements UserLoginService {

}
