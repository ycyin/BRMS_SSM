package com.yyc.vo;

public  enum ResultEnum {
        UNKNOW_ERROR(-1,"未知错误"),
        LOGIN_SUCCESS(10001,"恭喜您！登录成功！"),
        SELECT_SUCCESS(10002,"查询成功！"),
        DELETE_SUCCESS(10003,"删除成功！"),
        UPDATE_SUCCESS(10004,"更新成功！"),
        ADD_SUCCESS(10005,"添加成功！"),
        IMPORT_SUCCESS(10006,"导入成功！"),
        DISORDER_ADD_SUCCESS(10007,"分销订单添加成功！"),
        DISORDER_MODIFY_STATUS_SUCCESS(10008,"修改状态成功！"),
        DISORDER_CANCEL_SUCCESS(10009,"订单取消成功！"),
        UNLOGIN(20000,"未登录！"),
        NAMEORPASS_EXCEPTION(20001,"登录失败，用户名或密码错误"),
        LOGIN_NAMEORPASS_NULL(20002,"登录失败，用户名或密码为空"),
        HAS_NULL(20005,"数据中有空值"),
        SELECT_FAILD(20003,"查询失败！"),
        DELETE_FAILD(20004,"删除失败！"),
        UPDATE_FAILD(20006,"更新失败！"),
        ADD_FAILD(20007,"添加失败！"),
        IMPORT_FAILD_HAS_DUPLICATE(20008,"导入失败,数据重复！请删除重复数据再试"),
        IMPORT_FAILD_UNKNOW(20009,"导入失败,未知错误！"),
        ADD_FAILD_HAS_BOOK_DUPLICATE(20008,"添加失败,该本书已存在！"),
        ADD_FAILD_UNKNOW(20009,"添加失败,未知错误！"),
        UPDATE_FAILD_HAS_BOOK_DUPLICATE(20010,"更新失败,该本书已存在！"),
        UPDATE_FAILD_UNKNOW(20011,"更新失败,未知错误！"),
        ADD_FAILD_HAS_USER_DUPLICATE(20008,"添加失败,该用户名已存在！"),
        UPDATE_FAILD_HAS_USER_DUPLICATE(20010,"更新失败,该用户名已存在！"),
        THIS_ORDER_NOT_CANCEL(20011,"该订单已结束，不可取消！"),
        THIS_ORDER_NOT_MODIFY(20012,"该订单已结束，不可修改！"),
        DISORDER_MODIFY_STATUS_FAILED(20013,"修改状态失败，请重试！"),
        DISORDER_CANCEL_FAILED(20014,"订单取消失败，请重试！"),
        ;
        private Integer code;
        private String msg;
        ResultEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
    }