package com.songyang.api.controller.visitor;

import com.songyang.common.StandardResponse;
import com.songyang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visitor/book")
public class VBookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public StandardResponse getBookList(String categoryName, @RequestParam(defaultValue = "1")int pageNum,
                                        @RequestParam(defaultValue = "20")int pageSize){
       return bookService.getBookListByCategoryName(categoryName,pageSize,pageNum);
    }

    @GetMapping("/keyword")
    public StandardResponse getBookListByKeyWord(String keyword,
                                        @RequestParam(defaultValue = "1")int pageNum,
                                        @RequestParam(defaultValue = "20") int pageSize){
        return bookService.getBookListByKeyword(keyword,pageNum,pageSize);
    }



}
