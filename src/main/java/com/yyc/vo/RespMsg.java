package com.yyc.vo;

import java.io.Serializable;

public class RespMsg implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public RespMsg(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public RespMsg(ResultEnum renum, Object data) {
        this.code = renum.getCode();
        this.msg = renum.getMsg();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
