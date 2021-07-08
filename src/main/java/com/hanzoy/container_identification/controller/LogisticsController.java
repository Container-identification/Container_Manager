package com.hanzoy.container_identification.controller;

import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.dto.param.LogisticsParam;
import com.hanzoy.container_identification.service.LogisticsService;
import com.hanzoy.container_identification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsController {

    @Autowired
    UserService userService;

    @Autowired
    LogisticsService logisticsService;
    @PostMapping("/logistics")
    public CommonResult logistics(@RequestBody LogisticsParam param){
        userService.login(param.getToken());
        return logisticsService.logistics(param.getContainer());
    }
}
