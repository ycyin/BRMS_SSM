package com.yyc.entity;

/**
 * @program: SSM
 * @description: 图书类别元信息实体类
 * @author: yyc
 * @create: 2019-12-05 21:11
 **/
public class BookCategoryMeta {
    private Integer id;
    private String categoryName; //类别名
    private String description; //描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
