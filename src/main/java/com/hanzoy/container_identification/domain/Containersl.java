package com.hanzoy.container_identification.domain;

public class Containersl {
    private Integer orders;

    private Integer sta;

    private String container;

    private String owner;

    private Integer status;

    private String type;

    private String tare;

    private String payload;

    private String url;

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getSta() {
        return sta;
    }

    public void setSta(Integer sta) {
        this.sta = sta;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTare() {
        return tare;
    }

    public void setTare(String tare) {
        this.tare = tare;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orders=").append(orders);
        sb.append(", sta=").append(sta);
        sb.append(", container=").append(container);
        sb.append(", owner=").append(owner);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", tare=").append(tare);
        sb.append(", payload=").append(payload);
        sb.append(", url=").append(url);
        sb.append("]");
        return sb.toString();
    }
}