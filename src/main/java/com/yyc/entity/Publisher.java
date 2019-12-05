package com.yyc.entity;

/**
 * @program: SSM
 * @description: 出版社实体类
 * @author: yyc
 * @create: 2019-12-05 21:12
 **/
public class Publisher {
    private Integer id;
    private String pubName;//出版社名字
    private String pubAddr;//出版社地址
    private String pubPhone;//出版社电话
    private Integer pubCount;//图书本书

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubAddr() {
        return pubAddr;
    }

    public void setPubAddr(String pubAddr) {
        this.pubAddr = pubAddr;
    }

    public String getPubPhone() {
        return pubPhone;
    }

    public void setPubPhone(String pubPhone) {
        this.pubPhone = pubPhone;
    }

    public Integer getPubCount() {
        return pubCount;
    }

    public void setPubCount(Integer pubCount) {
        this.pubCount = pubCount;
    }
}
