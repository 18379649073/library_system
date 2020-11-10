package com.gec.books.service.impl;

import com.gec.books.mapper.BooksMapper;
import com.gec.books.pojo.Books;
import com.gec.books.pojo.BooksExample;
import com.gec.books.pojo.Result;
import com.gec.books.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksMapper booksMapper;

    public List<Books> findBooks() {
        List<Books> list = booksMapper.selectByExample(null);
        return list;
    }

    @Override
    public Result addBook(Books book) {
        // 给添加一个时间
        book.setTime(new Date());
        int n = this.booksMapper.insertSelective(book);
        System.out.println("n:" + n);
        Result result = new Result();
        if(n > 0){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
            result.setMessage("插入失败，请联系系统管理员！");
        }
        return result;
    }

    @Override
    public Result isExist(String name) {
        BooksExample example = new BooksExample();
        BooksExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Books> books = this.booksMapper.selectByExample(example);
        Result result = new Result();
        // 如果存在
        if(books.size() > 0){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public Books findBookById(Integer id) {
        return this.booksMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result editBookById(Books book) {
        int n = this.booksMapper.updateByPrimaryKeySelective(book);
        Result result = new Result();
        if(n > 0){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
            result.setMessage("修改失败，请联系管理员");
        }
        return result;
    }

    @Override
    public Result deleteBookById(Integer id) {
        int n = this.booksMapper.deleteByPrimaryKey(id);
        Result result = new Result();
        if(n > 0){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
            result.setMessage("删除失败，请联系管理员");
        }
        return result;
    }


}

