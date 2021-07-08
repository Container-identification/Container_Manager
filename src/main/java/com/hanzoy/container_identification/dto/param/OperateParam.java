package com.hanzoy.container_identification.dto.param;

import lombok.Data;

@Data
public class OperateParam {
    private String token;
    private String order;
    private String container;
    private String owner;
    private String type;
    private String tare;
    private String payLoad;
    private String url;
}
