package com.biye.paper.biz.questioncontent;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface QuestioncontentRepository {

    List<Map<String, String>> getQuestionContentList(Map<String, Object> param);

    QuestioncontentResponse getQuestionContentDetail(Map<String, Object> param);

    List<Map<String, String>> getQuestionContentListAccurate(Map<String, Object> param);

    int getQuestionContentCnt(Map<String, Object> param);

    int editQuestionContent(Map<String, Object> param);

    int addQuestionContent(Map<String, Object> param);

    int delQuestionContent(Map<String, String> param);
}
