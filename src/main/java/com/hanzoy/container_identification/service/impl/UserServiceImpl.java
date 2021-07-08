package com.hanzoy.container_identification.service.impl;

import com.hanzoy.container_identification.domain.User;
import com.hanzoy.container_identification.domain.UserExample;
import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.exception.exception.TokenErrorException;
import com.hanzoy.container_identification.mapper.UserMapper;
import com.hanzoy.container_identification.service.UserService;
import com.hanzoy.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Autowired
    JWTUtils jwtUtils;

    @Override
    public CommonResult login(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(username)
                .andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
//        System.out.println(users);
        if(users.isEmpty()){
            log.info("用户：{}登录失败，登录密码{}", username, password);
            return CommonResult.fail("1000", "用户密码错误");
        }
        String token = jwtUtils.createTokenCustomFields(users.get(0), "id", "username");
        log.info("用户：{}登录成功", username);
        HashMap<String, String> data = new HashMap<>();
        data.put("token", token);
        return CommonResult.success("0000", "登录成功", data);
    }

    @Override
    public void login(String token) {
        if(!jwtUtils.checkToken(token))
            throw new TokenErrorException("token验证失败");
    }

    @Override
    public User getUserByToken(String token) {
        return jwtUtils.getBean(token, User.class);
    }
}
