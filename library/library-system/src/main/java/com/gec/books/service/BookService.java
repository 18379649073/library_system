package com.gec.books.service;

import com.gec.books.pojo.Books;
import com.gec.books.pojo.BooksPage;
import com.gec.books.pojo.Result;

import java.util.List;

public interface BookService {
    public List<Books> findBooks();

    public Result addBook(Books book);

    public Result isExist(String name);

    public Books findBookById(Integer id);

    public Result editBookById(Books book);

    public Result deleteBookById(Integer id);

    public BooksPage findBooksByName(String name, Integer pageNum);

    public BooksPage findBooksPage(Integer pageNum);
}
