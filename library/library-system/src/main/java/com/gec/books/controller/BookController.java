package com.gec.books.controller;

import com.gec.books.pojo.Books;
import com.gec.books.pojo.BooksPage;
import com.gec.books.pojo.Result;
import com.gec.books.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "BookController",description = "图书系统操作接口")
@RestController  // 采用rest风格，返回的所有数据都是json格式
@RequestMapping("/Book")
@CrossOrigin("*")
public class BookController {


    @Autowired
    private BookService bookService;

    /**
     * 用于访问所有图书信息，
     * @return 返回值为List列表，里面封装图书对象
     */
    @ApiOperation("获取所有的图书信息")
    @GetMapping("/books")
    public List<Books> books(){
        List<Books> list = bookService.findBooks();
        System.out.println("books: \n"+list.toString());
        return list;
    }

    /**
     * 用于添加图书信息
     * @param book  单个图书信息对象
     * @return  返回一个Result结果对象
     */
    @ApiOperation("添加图书")
    @PostMapping("addBook")
    public Result addBook(@ApiParam("添加图书的图书对象，主要是图书名name") @RequestBody Books book){
        System.out.println("addBook:\n"+book);
        Result result = this.bookService.addBook(book);
        System.out.println(result);
        return result;
    }

    /**
     * 判断是否存在这个名字
     * @param name
     * @return
     */
    @ApiOperation("判断传入的图书名是否存在")
    @GetMapping("isExist")
    public Result isExist(@ApiParam("图书名") String name){
        System.out.println("isExist:\n name:"+name);
        Result result = this.bookService.isExist(name);
        System.out.println(result);
        return result;
    }

    /**
     * 查询单个图书信息
     * @param id
     * @return
     */
    @ApiOperation("通过图书id获取图书对象信息")
    @GetMapping("findBookById")
    public Books findBookById(@ApiParam("图书的id") Integer id){
        System.out.println("findBookById: \n id:"+id);
        Books book = this.bookService.findBookById(id);
        System.out.println(book);
        return book;
    }

    /**
     * 修改单个图书信息，通过id值
     * @param book
     * @return
     */
    @ApiOperation("通过图书id修改图书信息")
    @PostMapping("editBookById")
    public Result editBookById(@ApiParam("图书对象信息，通过id，改变其他值") @RequestBody Books book){
        System.out.println("editBookById: \n" + book);
        Result result = this.bookService.editBookById(book);
        System.out.println(result);
        return result;
    }

    /**
     * 通过id删除图书信息
     * @param id
     * @return
     */
    @ApiOperation("通过图书id删除图书")
    @GetMapping("deleteBookById")
    public Result deleteBookById(@ApiParam("图书id") Integer id){
        System.out.println("deleteBookById: \n id"+id);
        Result result = this.bookService.deleteBookById(id);
        System.out.println(result);
        return result;
    }

    /**
     * 分页获取数据
     * @param pageNum
     * @return
     */
    @ApiOperation("分页获取数据")
    @GetMapping("findBooksPage")
    public BooksPage findBooksPage(@ApiParam("当前页码") Integer pageNum){
        System.out.println("findBooksPage:\n pageNum:"+pageNum);
        BooksPage booksPage = this.bookService.findBooksPage(pageNum);
        System.out.println(booksPage);
        return booksPage;
    }

    /**
     * 分页加模糊查询
     * @param name
     * @param pageNum
     * @return
     */
    @ApiOperation("分页加模糊查询")
    @GetMapping("findBooksByName")
    public BooksPage findBooksByName(@ApiParam("图书名")  String name,@ApiParam("当前页码")  Integer pageNum){
        System.out.println("findBooksByName:\n name:"+name+"--pageNum:"+pageNum);
        BooksPage booksPage = this.bookService.findBooksByName(name,pageNum);
        System.out.println(booksPage);
        return booksPage;
    }
}
