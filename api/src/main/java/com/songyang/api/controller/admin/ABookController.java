package com.songyang.api.controller.admin;


import com.songyang.api.ImgPropoties.ImgType;
import com.songyang.common.DateformatUtil;
import com.songyang.common.StandardResponse;

import com.songyang.dao.BookMapper;
import com.songyang.pojo.Book;
import com.songyang.service.BookService;
import com.songyang.service.FileService;
import com.songyang.vo.BookQuery;
import com.songyang.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/admin/book")
public class ABookController {
    @Autowired
    FileService fileService;
    @Autowired
    private BookService bookService;
    @GetMapping("/list")
    public StandardResponse listbooks(@RequestParam(defaultValue = "10") int pageSize, int pageNum){
       return StandardResponse.SuccessResponse("查询成功",bookService.getBookList(pageSize,pageNum));
    }

    @PostMapping("/add")
    public StandardResponse insertBook(String bookname, Integer count , String author , String title, String publishTime, BigDecimal
                                       overduePrice, MultipartFile multipartFile,String categoryName,HttpServletRequest httpServletRequest){
        String path =httpServletRequest.getSession().getServletContext().getRealPath("upload");
        String picname= fileService.upload(multipartFile,path,ImgType.bookImagesPath);
        if(picname==null){
            return  StandardResponse.ErrorResponseMessage("失败");
        }
        Book book =new Book();
        book.setAuthor(author);
        book.setBorrowConut(0);
        book.setIsDelete(false);
        book.setName(bookname);
        book.setTitle(title);
        book.setOverduePrice(overduePrice);
        book.setConut(count);
        book.setPublishTime(DateformatUtil.strToDate(publishTime));
        book.setPicMainName(picname);
        book.setPicHost("http://r318x60291.zicp.vip/"+ImgType.bookImagesPath);

       if( bookService.insertBookandpic(book,categoryName)){
           return StandardResponse.SuccessResponse("添加成功",book);
       }else {
           return StandardResponse.ErrorResponseMessage("添加失败");
       }

    }
    @GetMapping(value = "/deletebook")
    public StandardResponse deleteBook(QueryVo queryVo){
       Boolean b =bookService.deleteBooks(queryVo.getIds());
      return b==true ? StandardResponse.SuccessResponseMessage("下架成功"):StandardResponse.ErrorResponseMessage("下架失败");
    }

    @PostMapping(value = "/updatebook/{id}")
    public StandardResponse updateBook(Book book,@PathVariable(value = "id") int id){
        book.setId(id);
       Boolean b=bookService.updateBook(book);

      return b==true ? StandardResponse.SuccessResponseMessage("更新成功"):StandardResponse.ErrorResponseMessage("更新失败");
    }
    @PostMapping(value = "/updatebook")
    public StandardResponse updateBook(BookQuery bookQuery){
        boolean b=bookService.updateBooks(bookQuery.getBookList());
        return b==true ? StandardResponse.SuccessResponseMessage("更新成功"):StandardResponse.ErrorResponseMessage("更新失败");
    }






}
