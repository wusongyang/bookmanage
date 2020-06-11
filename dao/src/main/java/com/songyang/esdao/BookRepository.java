package com.songyang.esdao;

import com.songyang.EsBook;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends ElasticsearchRepository<EsBook,Integer> {
}
