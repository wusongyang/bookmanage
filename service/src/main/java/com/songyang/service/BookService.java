package com.songyang.service;

import com.github.pagehelper.PageInfo;
import com.songyang.common.StandardResponse;
import com.songyang.pojo.Book;

import java.util.List;


public interface BookService {

    PageInfo getBookList(int pageSize, int pageNum);
    Boolean insertBookandpic(Book book ,String categoryName);
    Boolean deleteBooks(Integer[] ids);
    Boolean updateBook(Book book);
    Boolean updateBooks(List<Book> books);
    StandardResponse getBookListByCategoryName(String categoryName,int pageSize,int pageNum);

    StandardResponse getBookListByKeyword(String keyWord,int pageNum,int pageSize);
}
