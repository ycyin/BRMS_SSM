package com.yyc.vo.request;

/**
 * @program: SSM
 * @description: 用户信息
 * @author: yyc
 * @create: 2020-03-13 11:04
 **/
public class UserVo {
     private String username;//登录名
     private String nickname;//用户名
     private String password;//密码
     private int role;//角色
     private int state;//用户状态

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getNickname() {
          return nickname;
     }

     public void setNickname(String nickname) {
          this.nickname = nickname;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public int getRole() {
          return role;
     }

     public void setRole(int role) {
          this.role = role;
     }

     public int getState() {
          return state;
     }

     public void setState(int state) {
          this.state = state;
     }
}
