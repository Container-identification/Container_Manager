package com.hanzoy.container_identification.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Status_1 {
    @JsonIgnore
    private Integer id;

    @JsonIgnore
    private String container;

    private String operate;

    private String place;

    private String date;

    private String time;

    private String person;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", container=").append(container);
        sb.append(", operate=").append(operate);
        sb.append(", place=").append(place);
        sb.append(", date=").append(date);
        sb.append(", time=").append(time);
        sb.append(", person=").append(person);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}