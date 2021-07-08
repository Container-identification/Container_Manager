package com.hanzoy.container_identification.service;

import com.hanzoy.container_identification.domain.User;
import com.hanzoy.container_identification.dto.CommonResult;

public interface OrdersService {
    void insertOrder(String container,String path, User user);
}
