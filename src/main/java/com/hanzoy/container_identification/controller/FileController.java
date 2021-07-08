package com.hanzoy.container_identification.controller;


import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.service.FileService;
import com.hanzoy.container_identification.service.UserService;

import com.hanzoy.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @Autowired
    JWTUtils jwtUtils;

    @PostMapping("/upload")
    public CommonResult upload(@RequestParam MultipartFile file, @RequestParam String token) {
//        return "ok";
        userService.login(token);
        return fileService.upload(file, token);
    }
}
