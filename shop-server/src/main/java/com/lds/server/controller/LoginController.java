package com.lds.server.controller;

import com.alibaba.fastjson.JSON;
import com.lds.pojo.dto.UserDto;
import com.lds.pojo.po.User;
import com.lds.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public String token(@RequestBody UserDto userDto) {
        String token = userService.toLogin(userDto);
        return token;
    }

    @PostMapping("/register/1")
    public void test() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

        Object principalObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principalObj instanceof String) {
            //取出用户身份信息
            String principal = principalObj.toString();
            //将json转成对象
            System.out.println("principal = " + principal);
        }
    }

    @PostMapping("/register/2")
    public void test2() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

        Object principalObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principalObj instanceof String) {
            //取出用户身份信息
            String principal = principalObj.toString();
            //将json转成对象
            System.out.println("principal = " + principal);
        }
    }
}
