package com.hanzoy.container_identification.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Statistics_sort {
    @JsonIgnore
    private Integer id;

    @JsonIgnore
    private Integer infoId;

    private String name;

    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", infoId=").append(infoId);
        sb.append(", name=").append(name);
        sb.append(", count=").append(count);
        sb.append("]");
        return sb.toString();
    }
}