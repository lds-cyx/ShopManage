package com.lds.server.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lds.pojo.dto.AuthParamsDto;
import com.lds.pojo.dto.UserDto;
import com.lds.pojo.po.User;
import com.lds.server.mapper.UserMapper;
import com.lds.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));

        if (user == null){
            throw new RuntimeException("不存在");
        }

//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
        String password = user.getPassword();

        String[] authorities = {"test"};

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(JSON.toJSONString(user)).password(password).authorities(authorities).build();

        return userDetails;
    }

//    public UserDetails getUserPrincipal(UserDto user) {
//        String password = user.getPassword();
//        user.setPassword(null);
//        String userStr = JSON.toJSONString(user);
//        String[] authorities = {"test"};
//
//        return userDetails;
//    }


}
