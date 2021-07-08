package com.hanzoy.container_identification.service;

import com.hanzoy.container_identification.dto.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    CommonResult upload(MultipartFile file, String token);
}
