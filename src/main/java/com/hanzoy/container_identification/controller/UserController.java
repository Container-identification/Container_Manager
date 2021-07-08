package com.hanzoy.container_identification.controller;

import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.dto.param.LoginParam;
import com.hanzoy.container_identification.service.UserService;
import com.hanzoy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController

public class UserController {

    @Resource
    UserService userService;

    @Autowired
    JWTUtils jwtUtils;
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginParam param){
        return userService.login(param.getUsername(), param.getPassword());
    }
}
