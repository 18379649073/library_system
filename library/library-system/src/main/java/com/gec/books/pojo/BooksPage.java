package com.gec.books.pojo;

import java.util.List;

public class BooksPage {

    private List<Books> books;

    private Long total;

    private Integer pages;

    @Override
    public String toString() {
        return "BooksPage{" +
                "books=" + books +
                ", total=" + total +
                ", pages=" + pages +
                '}';
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
