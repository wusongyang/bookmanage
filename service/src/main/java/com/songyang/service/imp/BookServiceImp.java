package com.songyang.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songyang.dao.BookMapper;
import com.songyang.dao.BookPicMapper;
import com.songyang.dao.CategoryMapper;
import com.songyang.pojo.Book;
import com.songyang.pojo.BookPic;
import com.songyang.pojo.Category;
import com.songyang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
@Transactional
public class BookServiceImp implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BookPicMapper bookPicMapper;
    @Transactional
    @Override
    public PageInfo getBookList(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List list =bookMapper.selectBookList();
        PageInfo pageInfo =new PageInfo(list);
        return  pageInfo;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertBookandpic(Book book ,String categoryName) {
        int categoryId = categoryMapper.selectCategoryIdByname(categoryName);
        if(categoryId ==0){
            return false;
        }
        book.setCategoryId(categoryId);
        bookMapper.insertSelective(book);
        BookPic bookPic =new BookPic();
        bookPic.setBookId(book.getId());
        bookPic.setPicHost(book.getPicHost());
        bookPic.setPicName(book.getPicMainName());
      int conut=bookPicMapper.insertSelective(bookPic);
      if (conut>0){
          return true;
      }else return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteBooks(Integer[] ids) {
       int count= bookMapper.deleteBookBylists(ids);
    if (count==ids.length){
        return true;
    }else {
        return false;
    }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateBook(Book book) {
        int count =bookMapper.updateByPrimaryKeySelective(book);
        if (count>0){
            return true;
        }else return  false;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBooks(List<Book> books) {
       int count= bookMapper.updateBooks(books);
       if(count>0){
           return true;
       }else return false;
    }

}
