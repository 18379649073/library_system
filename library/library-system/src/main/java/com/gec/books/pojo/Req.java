package com.gec.books.pojo;

public class Req {

    private String name;
    private Integer pageNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "Req{" +
                "name='" + name + '\'' +
                ", pageNum=" + pageNum +
                '}';
    }
}
