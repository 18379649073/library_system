package com.gec.books.pojo;

/**
 * 此类是一个返回操作信息的类
 */
public class Result {
    // 用于表示添加、删除、修改 是否成功，true表示成功，false表示失败
    private boolean isSuccess;
    // 用于返回提示信息
    private String message;
    // 用户返回可能会用到的数据
    private Object  data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
