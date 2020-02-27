package com.biye.paper.biz.newscontent;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NewscontentRepository {

    List<Map<String, String>> getNewContentList(Map<String, Object> param);

    int getNewContentCnt(Map<String, Object> param);

    int delNewContent(Map<String, String> param);
}
