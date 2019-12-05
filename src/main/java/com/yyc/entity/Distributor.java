package com.yyc.entity;

/**
 * @program: SSM
 * @description: 分销商实体类
 * @author: yyc
 * @create: 2019-12-05 19:49
 **/
public class Distributor {
    private Integer id;
    private String disName;//分销商名字
    private String disAddr;//分销商地址
    private String disPhone;//分销商电话
    private Integer disCount;//分销商分销图书总量
    private Integer disRank;//分销商等级
    private Integer disParent;//分销商上级编号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisAddr() {
        return disAddr;
    }

    public void setDisAddr(String disAddr) {
        this.disAddr = disAddr;
    }

    public String getDisPhone() {
        return disPhone;
    }

    public void setDisPhone(String disPhone) {
        this.disPhone = disPhone;
    }

    public Integer getDisCount() {
        return disCount;
    }

    public void setDisCount(Integer disCount) {
        this.disCount = disCount;
    }

    public Integer getDisRank() {
        return disRank;
    }

    public void setDisRank(Integer disRank) {
        this.disRank = disRank;
    }

    public Integer getDisParent() {
        return disParent;
    }

    public void setDisParent(Integer disParent) {
        this.disParent = disParent;
    }
}
