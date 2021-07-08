package com.hanzoy.container_identification.domain;

public class Statistics_info {
    private Integer id;

    private Integer total;

    private Integer totalweight;

    private Integer usedweight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalweight() {
        return totalweight;
    }

    public void setTotalweight(Integer totalweight) {
        this.totalweight = totalweight;
    }

    public Integer getUsedweight() {
        return usedweight;
    }

    public void setUsedweight(Integer usedweight) {
        this.usedweight = usedweight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", total=").append(total);
        sb.append(", totalweight=").append(totalweight);
        sb.append(", usedweight=").append(usedweight);
        sb.append("]");
        return sb.toString();
    }
}