package com.biye.paper.biz.front;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FrontRepository {

    /**
     * 查询新闻类型列表.
     */
    List<Map<String, String>> getNewMenu();

    /**
     * 查询常见问题类型列表.
     */
    List<Map<String, String>> getQueMenu();

    /**
     * 查询新闻一览.
     */
    List<Map<String, String>> getNewsList(Map<String, Object> param);

    /**
     * 查询新闻一览条数.
     */
    int getNewsCnt(Map<String, Object> param);
}
