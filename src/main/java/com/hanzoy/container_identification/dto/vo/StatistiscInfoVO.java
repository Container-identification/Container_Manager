package com.hanzoy.container_identification.dto.vo;

import lombok.Data;

import java.util.ArrayList;
@Data
public class StatistiscInfoVO {
    private ArrayList<Info> info;

    @Data
    public static class Info{
        private String total;
        private String totalWeight;
        private String usedWeight;
        private ArrayList<Sort> sort;
        private ArrayList<Other> other;

        @Data
        public static class Sort{
            private String name;
            private String count;
        }
        @Data
        public static class Other{
            private String name;
            private String content;
        }
    }
}
