package com.biye.paper.biz.questiontype;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface QuestiontypeRepository {

    List<Map<String, String>> getQuestionTypeList(Map<String, Object> param);

    int getQuestionTypeCnt(Map<String, Object> param);

    List<Map<String, String>> getQuestionTypeListAccurate(Map<String, Object> param);

    int addQuestionType(Map<String, Object> param);

    int editQuestionType(Map<String, Object> param);

    int delQuestionType(Map<String, String> param);
}
