/**
 * Copyright (c) 2016
 * Created with BaseEntity.java
 *
 */
package com.rogchen.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Entity支持类
 */
public abstract class BaseEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前实体分页对象
     */
    @JsonIgnore
    protected Page<T> page;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
    @JsonIgnore
    protected Map<String, String> sqlMap;


    public BaseEntity() {

    }

    public Page<T> getPage() {
        if (page == null){
            page = new Page<T>();
        }
        return page;
    }

    public Page<T> setPage(Page<T> page) {
        this.page = page;
        return page;
    }

    public Map<String, String> getSqlMap() {
        if (sqlMap == null){
            sqlMap = Maps.newHashMap();
        }
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }


    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        /*BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());*/
        return false;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
