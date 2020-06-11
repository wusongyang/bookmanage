package com.songyang.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.songyang.EsBook;
import com.songyang.common.StandardResponse;
import com.songyang.dao.BookMapper;
import com.songyang.dao.BookPicMapper;
import com.songyang.dao.CategoryMapper;
import com.songyang.esdao.BookRepository;
import com.songyang.pojo.Book;
import com.songyang.pojo.BookPic;
import com.songyang.pojo.Category;
import com.songyang.service.BookService;
import com.songyang.service.CategoryService;
import com.songyang.vo.BookVo;
import com.songyang.vo.CategoryVo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookServiceImp implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BookPicMapper bookPicMapper;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StandardResponse getBookListByCategoryName(String categoryName ,int pageSize,int pageNum) {
       int categoryId = categoryMapper.selectCategoryIdByname(categoryName);
        PageHelper.startPage(pageNum,pageSize);
        List<Book> bookList = bookMapper.selectBookListByCategoryId(categoryId);
        if (bookList==null){
            return StandardResponse.ErrorResponseMessage("查询失败");
        }
        List<BookVo>  bookVos =Lists.newArrayList();
        for (Book book:
              bookList) {
            BookVo bookVo =this.assembleBook2BookVo(book);
            bookVos.add(bookVo);
        }
        PageInfo pageInfo =new PageInfo();
        pageInfo.setList(bookVos);
        return StandardResponse.SuccessResponse("success",pageInfo);
    }

    @Override
    public StandardResponse getBookListByKeyword(String keyWord, int pageNum, int pageSize) {
        if (keyWord==null){
            return StandardResponse.ErrorResponseMessage("关键词为空");
        }
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder =QueryBuilders.boolQuery();

        MatchQueryBuilder matchQueryBuilder1 =QueryBuilders.matchQuery(EsBook.Type.NAME,keyWord);

        MatchQueryBuilder matchQueryBuilder2 =QueryBuilders.matchQuery(EsBook.Type.AUTHOR,keyWord);


        boolQueryBuilder.should(matchQueryBuilder1).should(matchQueryBuilder2);
        System.out.println(boolQueryBuilder);
        NativeSearchQuery build = nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum-1, pageSize)).withQuery(boolQueryBuilder).build();
//        SearchHits<EsBook> search = restTemplate.search(build, EsBook.class);
//        search.getSearchHits();
        Page<EsBook> esBookPage = bookRepository.search(build);
        esBookPage.forEach(esBook -> System.out.println(esBook.getName()));
        if (esBookPage.getTotalElements()==0){
            return StandardResponse.SuccessResponseMessage("没有相关数据");
        }else return  StandardResponse.SuccessResponse("查询成功",esBookPage);

    }

    private BookVo assembleBook2BookVo(Book book){
            BookVo bookVo =new BookVo();
            bookVo.setAuthor(book.getAuthor());
            bookVo.setCount(book.getCount());
            bookVo.setBorrowCount(book.getBorrowCount());
            bookVo.setDelete(book.getIsDelete());
            bookVo.setId(book.getId());
            bookVo.setName(book.getName());
            bookVo.setOverduePrice(book.getOverduePrice());
            bookVo.setPublishTime(book.getPublishTime());
            bookVo.setPicHost(book.getPicHost());
            bookVo.setPicMainName(book.getPicMainName());
            bookVo.setTitle(book.getTitle());
            Set<Category> set = (Set) categoryService.getCategoryAndParentSet(book.getCategoryId()).getData();
            List<CategoryVo> categoryVos = Lists.newArrayList();
            for (Category category:
                    set) {
                CategoryVo categoryVo=new CategoryVo();
                categoryVo.setCategoryName(category.getName());
                categoryVos.add(categoryVo);
            }
            bookVo.setCategoryVos(categoryVos);
        return bookVo;
        }



}
