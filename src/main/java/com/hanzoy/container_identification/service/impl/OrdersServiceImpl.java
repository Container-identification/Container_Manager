package com.hanzoy.container_identification.service.impl;

import com.hanzoy.container_identification.domain.Orders;
import com.hanzoy.container_identification.domain.User;
import com.hanzoy.container_identification.mapper.OrdersMapper;
import com.hanzoy.container_identification.service.OrdersService;
import com.hanzoy.container_identification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    OrdersMapper ordersMapper;

    @Autowired
    UserService userService;

    @Override
    public void insertOrder(String container,String path, User user) {
        Orders orders = new Orders();
        orders.setContainer(container);
        orders.setUserId(user.getId());
        orders.setUrl(path);
        orders.setTime(new Date());
        ordersMapper.insert(orders);
    }
}
