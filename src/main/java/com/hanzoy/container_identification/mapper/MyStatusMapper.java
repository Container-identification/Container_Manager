package com.hanzoy.container_identification.mapper;

import com.hanzoy.container_identification.domain.Status_1;

public interface MyStatusMapper {
    void insertStatus(Status_1 status);

    String selectConByOrder(String order);
}
