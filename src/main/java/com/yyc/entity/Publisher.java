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

    public Publisher() {
    }

    public Publisher(String pubName) {
        this.pubName = pubName;
    }

    public Publisher(String pubName, String pubAddr, String pubPhone, Integer pubCount) {
        this.pubName = pubName;
        this.pubAddr = pubAddr;
        this.pubPhone = pubPhone;
        this.pubCount = pubCount;
    }

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

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", pubName='" + pubName + '\'' +
                ", pubAddr='" + pubAddr + '\'' +
                ", pubPhone='" + pubPhone + '\'' +
                ", pubCount=" + pubCount +
                '}';
    }
}
