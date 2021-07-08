package com.hanzoy.container_identification.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Status {
    @JsonIgnore
    private Integer id;

    @JsonIgnore
    private String container;

    private String operate;

    private String place;

    private String date;

    private String time;

    private String method;

    private String voyage;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
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
        sb.append(", method=").append(method);
        sb.append(", voyage=").append(voyage);
        sb.append("]");
        return sb.toString();
    }
}