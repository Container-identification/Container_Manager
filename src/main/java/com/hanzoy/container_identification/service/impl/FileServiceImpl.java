package com.hanzoy.container_identification.service.impl;

import com.hanzoy.container_identification.domain.User;
import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.service.FileService;
import com.hanzoy.container_identification.service.OrdersService;
import com.hanzoy.container_identification.service.UserService;
import com.hanzoy.container_identification.utils.UuidUtil;
import com.hanzoy.container_identification.utils.pythonSocket.socket_nofile;
import com.hanzoy.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    OrdersService ordersService;

    @Autowired
    UserService userService;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    socket_nofile socket;

    @Override
    public CommonResult upload(MultipartFile file, String token) {
//        创建文件夹存储文件
        String key = UuidUtil.getShortUuid();
        String filePath = "A:/temp/jzx/" + key;
        String fullPath = filePath + "/1.jpg";
        String URL = "/images/"+key+"/1.jpg";
        File path = new File(filePath);
        if(path.mkdir())
            log.info("创建临时文件夹 {}", key);
        else
            log.info("创建临时文件夹 {} 失败", key);
        File dest = new File(fullPath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        后端算法调用
        System.out.println("test");
        String container = socket.remoteCall(fullPath);
//        String container = "container";
        System.out.println("test");
//        延时
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("图片临时存储路径{}", fullPath);

        ordersService.insertOrder(container, URL, jwtUtils.getBean(token, User.class));

        //删除上传的图片文件
//        recursiveDelete(path);
        HashMap<String, Object> data = new HashMap<>();
        data.put("container", container);
        data.put("URL", "/huiImages/1.jpg");
        return CommonResult.success("0000", "success", data);
    }

    private void recursiveDelete(File file) {
        // 结束递归循环
        if (!file.exists())
            return;
        // 如果是目录，请进入内部并递归调用
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                // 调用递归
                recursiveDelete(f);
            }
        }
        // 调用delete删除文件和空目录
        file.delete();
        log.info("删除文件或文件夹{}", file.getAbsolutePath());
    }
}
