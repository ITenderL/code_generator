<#assign myParentDir="domain">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

<#include "/copyright_class.include" >
public class ${className}Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ${className}Example() {
        this.oredCriteria = new ArrayList<>();
    }

    public String getOrderByClause() {
        return this.orderByClause;
    }

    public void setOrderByClause(final String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return this.distinct;
    }

    public void setDistinct(final boolean distinct) {
        this.distinct = distinct;
    }

    public List<Criteria> getOredCriteria() {
        return this.oredCriteria;
    }

    public void or(final Criteria criteria) {
        this.oredCriteria.add(criteria);
    }

    public Criteria or() {
        final Criteria criteria = createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        final Criteria criteria = createCriteriaInternal();
        if (this.oredCriteria.size() == 0) {
            this.oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        this.oredCriteria.clear();
        this.orderByClause = null;
        this.distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            this.criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return this.criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return this.criteria;
        }

        public List<Criterion> getCriteria() {
            return this.criteria;
        }

        protected void addCriterion(final String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            this.criteria.add(new Criterion(condition));
        }

        protected void addCriterion(final String condition, final Object value, final String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            this.criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(final String condition, final Object value1, final Object value2,
                                    final String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            this.criteria.add(new Criterion(condition, value1, value2));
        }

    	<#list table.columns as column>
    	<#if column.sqlName!="id">

    	public Criteria and${column.columnNameFirstUpper}IsNull() {
            addCriterion("${column.columnNameFirstLower} is null");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}IsNotNull() {
            addCriterion("${column.columnNameFirstLower} is not null");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}EqualTo(final ${column.javaType} value) {
            addCriterion("${column.sqlName} =", value, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}NotEqualTo(final ${column.javaType} value) {
            addCriterion("${column.sqlName} <>", value, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}GreaterThan(final ${column.javaType} value) {
            addCriterion("${column.sqlName} >", value, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}GreaterThanOrEqualTo(final ${column.javaType} value) {
            addCriterion("${column.sqlName} >=", value, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}LessThan(final ${column.javaType} value) {
            addCriterion("${column.sqlName} <", value, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}LessThanOrEqualTo(final ${column.javaType} value) {
            addCriterion("${column.sqlName} <=", value, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}In(final List<${column.javaType}> values) {
            addCriterion("${column.sqlName} in", values, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}NotIn(final List<${column.javaType}> values) {
            addCriterion("${column.sqlName} not in", values, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}Between(final ${column.javaType} value1, final ${column.javaType} value2) {
            addCriterion("${column.sqlName} between", value1, value2, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }

        public Criteria and${column.columnNameFirstUpper}NotBetween(final ${column.javaType} value1, final ${column.javaType} value2) {
            addCriterion("${column.sqlName} not between", value1, value2, "${column.columnNameFirstLower}");
            return (Criteria)this;
        }
        
    	</#if>
    	</#list>
        
        

      
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andFieldLike(final String fieldName, final String keyword) {
            addCriterion(fieldName + " like ", keyword, fieldName);
            return this;
        }
        public Criteria andFieldEqual(final String fieldName, final String keyword) {
            addCriterion(fieldName + " = ", keyword, fieldName);
            return this;
        }
    }

    public static class Criterion {
        private final String condition;
        private final String typeHandler;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;

        protected Criterion(final String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(final String condition, final Object value, final String typeHandler) {
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

        protected Criterion(final String condition, final Object value) {
            this(condition, value, null);
        }

        protected Criterion(final String condition, final Object value, final Object secondValue,
                            final String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(final String condition, final Object value, final Object secondValue) {
            this(condition, value, secondValue, null);
        }

        public String getCondition() {
            return this.condition;
        }

        public Object getValue() {
            return this.value;
        }

        public Object getSecondValue() {
            return this.secondValue;
        }

        public boolean isNoValue() {
            return this.noValue;
        }

        public boolean isSingleValue() {
            return this.singleValue;
        }

        public boolean isBetweenValue() {
            return this.betweenValue;
        }

        public boolean isListValue() {
            return this.listValue;
        }

        public String getTypeHandler() {
            return this.typeHandler;
        }
    }
}