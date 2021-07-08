package com.hanzoy.container_identification.domain;

import java.util.ArrayList;
import java.util.List;

public class Statistics_infoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Statistics_infoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalweightIsNull() {
            addCriterion("totalWeight is null");
            return (Criteria) this;
        }

        public Criteria andTotalweightIsNotNull() {
            addCriterion("totalWeight is not null");
            return (Criteria) this;
        }

        public Criteria andTotalweightEqualTo(Integer value) {
            addCriterion("totalWeight =", value, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightNotEqualTo(Integer value) {
            addCriterion("totalWeight <>", value, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightGreaterThan(Integer value) {
            addCriterion("totalWeight >", value, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalWeight >=", value, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightLessThan(Integer value) {
            addCriterion("totalWeight <", value, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightLessThanOrEqualTo(Integer value) {
            addCriterion("totalWeight <=", value, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightIn(List<Integer> values) {
            addCriterion("totalWeight in", values, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightNotIn(List<Integer> values) {
            addCriterion("totalWeight not in", values, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightBetween(Integer value1, Integer value2) {
            addCriterion("totalWeight between", value1, value2, "totalweight");
            return (Criteria) this;
        }

        public Criteria andTotalweightNotBetween(Integer value1, Integer value2) {
            addCriterion("totalWeight not between", value1, value2, "totalweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightIsNull() {
            addCriterion("usedWeight is null");
            return (Criteria) this;
        }

        public Criteria andUsedweightIsNotNull() {
            addCriterion("usedWeight is not null");
            return (Criteria) this;
        }

        public Criteria andUsedweightEqualTo(Integer value) {
            addCriterion("usedWeight =", value, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightNotEqualTo(Integer value) {
            addCriterion("usedWeight <>", value, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightGreaterThan(Integer value) {
            addCriterion("usedWeight >", value, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightGreaterThanOrEqualTo(Integer value) {
            addCriterion("usedWeight >=", value, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightLessThan(Integer value) {
            addCriterion("usedWeight <", value, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightLessThanOrEqualTo(Integer value) {
            addCriterion("usedWeight <=", value, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightIn(List<Integer> values) {
            addCriterion("usedWeight in", values, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightNotIn(List<Integer> values) {
            addCriterion("usedWeight not in", values, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightBetween(Integer value1, Integer value2) {
            addCriterion("usedWeight between", value1, value2, "usedweight");
            return (Criteria) this;
        }

        public Criteria andUsedweightNotBetween(Integer value1, Integer value2) {
            addCriterion("usedWeight not between", value1, value2, "usedweight");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}