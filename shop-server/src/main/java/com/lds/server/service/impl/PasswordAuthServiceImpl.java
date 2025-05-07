package com.lds.server.service.impl;

import cn.hutool.core.bean.BeanUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lds.pojo.dto.AuthParamsDto;
import com.lds.pojo.dto.UserDto;
import com.lds.pojo.po.User;
import com.lds.server.mapper.UserMapper;
import com.lds.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service("password_authService")
@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordAuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto execute(AuthParamsDto authParamsDto) {

        String username = authParamsDto.getUsername();

        // 根据username账号查询数据库
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        // 查询到用户不存在 返回null即可，SpringSecurity框架会抛出异常用户不存在
        // 但是这边脱离了 loadUserByUsername这个方法 所以要抛出异常
        if (user == null) {
            throw new RuntimeException("用户不存在！");
//            ShopException.cast("用户不存在!");
        }

        // 如果查到了用户 我们拿到正确的密码，最终封装成一个UserDetails对象给SpringSecurity框架返回，由框架进行密码比对
        String password = user.getPassword();

        String passwordInput = authParamsDto.getPassword();
        boolean matches = passwordEncoder.matches(passwordInput, password);
        if (!matches) {
            throw new RuntimeException("密码错误!");
//            ShopException.cast("密码错误!");
        }

        UserDto userDto = BeanUtil.copyProperties(user, UserDto.class);

        return userDto;
    }

}
