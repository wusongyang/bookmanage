package com.songyang.dao;

import com.songyang.EsBook;
import com.songyang.esdao.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()

class DaoApplicationTests {
    @Autowired
    private BookRepository bookRepository;
    @Test
    void contextLoads() {
    }
    @Test
    void testFind(){
        Iterable<EsBook> esBookIterable=bookRepository.findAll();
        esBookIterable.forEach(esbook-> System.out.println(esbook.getAuthor()));
    }
}
