package com.yyc.dto;

/**
 * @program: SSM
 * @description: 用户基本信息DTO
 * @author: yyc
 * @create: 2020-03-11 15:15
 **/
public class UserDTO {
    private Integer id;//用户ID
    private String nickName;//用户名
    private byte state;//用户状态
    private String userName;//登录名
    private String role;//用户角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
