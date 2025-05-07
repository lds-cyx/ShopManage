package com.lds.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.lds.common.properties.JwtProperties;
import com.lds.pojo.dto.UserDto;
import com.lds.pojo.po.User;
import com.lds.server.service.UserService;
import com.lds.server.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String toLogin(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDto.getUsername(), userDto.getPassword()));

        if (Objects.isNull(authentication)){
            throw new RuntimeException("账号或者密码错误!");
        }

        //获取userid 生成token
        org.springframework.security.core.userdetails.User user = (org.springframework.
                security.core.userdetails.User) authentication.getPrincipal();
        String userId = JSON.parseObject(user.getUsername(), User.class).getId().toString();

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("authorities",authorities);

        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), map);

        return token;
    }
}
