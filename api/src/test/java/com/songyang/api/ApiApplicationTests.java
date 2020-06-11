package com.songyang.api;

import com.songyang.EsBook;
import com.songyang.esdao.BookRepository;
import com.songyang.pojo.Book;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.annotation.Native;
import java.util.List;

@SpringBootTest
class ApiApplicationTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ElasticsearchRestTemplate template;

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("wsysni8"));
    }

    @Test
    void testFind(){
        Iterable<EsBook> esBookIterable=bookRepository.findAll();
        esBookIterable.forEach(esbook-> System.out.println(esbook.getAuthor()));
    }
    @Test
    public void testBaseQuery(){
        // 词条查询
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "后来我");
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery searchQuery =searchQueryBuilder.withQuery(queryBuilder).withPageable(PageRequest.of(1,5)).build();
        // 执行查询
       SearchHits<EsBook> esBookPage=template.search(searchQuery,EsBook.class);
       esBookPage.forEach(esBook -> {
       });

    }

    


}
