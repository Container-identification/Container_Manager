package com.hanzoy.container_identification.service;

import com.hanzoy.container_identification.domain.User;
import com.hanzoy.container_identification.dto.CommonResult;

public interface UserService {
    CommonResult login(String username, String password);
    void login(String token);
    User getUserByToken(String token);
}
