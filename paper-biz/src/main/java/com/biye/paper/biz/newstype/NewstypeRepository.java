package com.biye.paper.biz.newstype;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NewstypeRepository {

    List<Map<String, String>> getNewTypeList(Map<String, Object> param);

    int getNewTypeCnt(Map<String, Object> param);

    List<Map<String, String>> getNewTypeListAccurate(Map<String, Object> param);

    int addNewType(Map<String, Object> param);

    int editNewType(Map<String, Object> param);

    int delNewType(Map<String, String> param);
}
