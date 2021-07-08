package com.hanzoy.container_identification.service;

import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.dto.param.DeleteParam;
import com.hanzoy.container_identification.dto.param.OperateParam;

public interface YardService {
    CommonResult queryYard();

    CommonResult yardStatistics();

    CommonResult operate(OperateParam param);

    CommonResult delete(DeleteParam param);
}
