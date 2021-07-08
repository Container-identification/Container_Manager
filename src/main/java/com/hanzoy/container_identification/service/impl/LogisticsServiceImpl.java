package com.hanzoy.container_identification.service.impl;

import com.hanzoy.container_identification.domain.*;
import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.mapper.ContainersMapper;
import com.hanzoy.container_identification.mapper.ContainerslMapper;
import com.hanzoy.container_identification.mapper.StatusMapper;
import com.hanzoy.container_identification.mapper.Status_1Mapper;
import com.hanzoy.container_identification.service.LogisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class LogisticsServiceImpl implements LogisticsService {

    @Resource
    StatusMapper statusMapper;

    @Resource
    Status_1Mapper status_1Mapper;

    @Resource
    ContainersMapper containersMapper;

    @Resource
    ContainerslMapper containerslMapper;

    @Override
    public CommonResult logistics(String container) {
        ContainerslExample containerslExample = new ContainerslExample();
        containerslExample.createCriteria()
                .andContainerEqualTo(container);
        List<Containersl> containers = containerslMapper.selectByExample(containerslExample);
        if(containers.isEmpty()){
            return CommonResult.fail("1002", "未查询到集装箱号");
        }
        Containersl theContainer = containers.get(0);

        HashMap<String, Object> data = new HashMap<>();
        data.put("container", theContainer.getContainer());
        data.put("owner", theContainer.getOwner());
        data.put("status", theContainer.getStatus());
        data.put("type", theContainer.getType());
        data.put("tare", theContainer.getTare());
        data.put("payLoad", theContainer.getPayload());

        if(theContainer.getStatus() == 0){
            StatusExample statusExample = new StatusExample();
            statusExample.createCriteria()
                    .andContainerEqualTo(container);
            List<Status> statuses = statusMapper.selectByExample(statusExample);
            data.put("table", statuses);
        }else {
            Status_1Example statusExample = new Status_1Example();
            statusExample.createCriteria()
                    .andContainerEqualTo(container);
            List<Status_1> statuses = status_1Mapper.selectByExample(statusExample);
            data.put("table", statuses);
        }

        return CommonResult.success(data);
    }
}
