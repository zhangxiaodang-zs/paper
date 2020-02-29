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

    /**
     * 获取新闻详细内容.
     */
    Map<String, String> getNewsContent(Map<String, String> param);

    /**
     * 查询问题一览.
     */
    List<Map<String, String>> getQuestionList(Map<String, Object> param);
    /**
     * 查询问题一览条数.
     */
    int getQuestionCnt(Map<String, Object> param);
    /**
     * 新闻阅读量
     */
    int addNewsReadNum(Map<String, String> param);
    /**
     * 新闻点赞
     */
    int addNewsGoodtimes(Map<String, String> param);
}
