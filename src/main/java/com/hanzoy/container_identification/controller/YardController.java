package com.hanzoy.container_identification.controller;

import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.dto.param.DeleteParam;
import com.hanzoy.container_identification.dto.param.OperateParam;
import com.hanzoy.container_identification.dto.param.QueryYardParam;
import com.hanzoy.container_identification.dto.param.yardStatisticsParam;
import com.hanzoy.container_identification.service.UserService;
import com.hanzoy.container_identification.service.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YardController {

    @Autowired
    UserService userService;

    @Autowired
    YardService yardService;

    @PostMapping("/queryYard")
    public CommonResult queryYard(@RequestBody QueryYardParam param){
        userService.login(param.getToken());
        return yardService.queryYard();
    }

    @PostMapping("/yardStatistics")
    public CommonResult yardStatistics(@RequestBody yardStatisticsParam param){
        userService.login(param.getToken());
        return yardService.yardStatistics();
    }

    @PostMapping("/operate")
    public CommonResult operate(@RequestBody OperateParam param){
        userService.login(param.getToken());
        return yardService.operate(param);
    }

    @PostMapping("/delete")
    public CommonResult delete(@RequestBody DeleteParam param){
        userService.login(param.getToken());
        return yardService.delete(param);
    }
}
