package com.biye.paper.biz.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FrontService {

    @Autowired
    private FrontRepository frontRepository;

    /**
     * 获取二级菜单.
     */
    public String getSecondMenusService() {

        FrontResponse frontResponse = new FrontResponse();
        frontResponse.setNewslist(this.frontRepository.getNewMenu());
        frontResponse.setQuelist(this.frontRepository.getQueMenu());

        log.info("获取二级菜单返回的数据为:\n{}", JSON.toJSONString(frontResponse,
                SerializerFeature.PrettyFormat
        ));

        return JSON.toJSONString(frontResponse);
    }

    /**
     * 获取新闻列表.
     */
    public String getNewsListService(FrontRequest requestData) {
        Map<String, Object> param = new HashMap<>();
        param.put("typeid", requestData.getNewstypeid());

        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        } else {
            param.put("startindex", 0);
            param.put("pagesize", 5);
            param.put("pagingOrNot", "1");
        }

        // 查询
        List<Map<String, String>> newsList = this.frontRepository.getNewsList(param);
        // 总条数
        int cnt = this.frontRepository.getNewsCnt(param);

        // 没有分页信息时
        if (requestData.getPagesize() == 0) {
            newsList.forEach(item -> {
                if (item.get("title").length() > 14) {
                    item.put("title", item.get("title").substring(0, 13) + "...");
                }
            });
        }

        FrontResponse responseData = new FrontResponse();
        responseData.setNewscontentlist(newsList);
        responseData.setTotalcount(cnt);
        log.info("返回的新闻一览数据为：\n{}", JSON.toJSONString(
                responseData,
                SerializerFeature.PrettyFormat
        ));

        return JSON.toJSONString(responseData);
    }

    /**
     * 获取新闻内容.
     */
    public String getNewsContentService(FrontRequest requestData) {
        Map<String, String> param = new HashMap<>();
        param.put("newsid", requestData.getNewsid());

        // 查询
        Map<String, String> newsContent = this.frontRepository.getNewsContent(param);
        newsContent.put("read", newsContent.get("readtimes"));
        newsContent.put("like", newsContent.get("goodtimes"));
        FrontResponse responseData = new FrontResponse();
        responseData.setNewscontent(newsContent);
        log.info("返回的新闻内容数据为：\n{}", JSON.toJSONString(
                responseData,
                SerializerFeature.PrettyFormat
        ));
        // 阅读量
        int readnum = this.frontRepository.addNewsReadNum(param);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 新闻点赞.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addNewsGoodtimes(FrontRequest requestData) {
        Map<String, String> param = new HashMap<>();
        param.put("newsid", requestData.getNewsid());

        // 点赞
        int addgood = this.frontRepository.addNewsGoodtimes(param);

        // 返回
        return JSON.toJSONString(addgood);
    }

    /**
     * 获取问题列表.
     */
    public String getQuestionListService(FrontRequest requestData) {
        Map<String, Object> param = new HashMap<>();
        param.put("typeid", requestData.getQuestiontypeid());

        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        } else {
            param.put("startindex", 0);
            param.put("pagesize", 5);
            param.put("pagingOrNot", "1");
        }

        // 查询
        List<Map<String, String>> questionList = this.frontRepository.getQuestionList(param);
        // 总条数
        int cnt = this.frontRepository.getQuestionCnt(param);

        // 没有分页信息时
        if (requestData.getPagesize() == 0) {
            questionList.forEach(item -> {
                if (item.get("title").length() > 14) {
                    item.put("title", item.get("title").substring(0, 13) + "...");
                }
            });
        }

        FrontResponse responseData = new FrontResponse();
        responseData.setQuestioncontentlist(questionList);
        responseData.setTotalcount(cnt);
        log.info("返回的新闻一览数据为：\n{}", JSON.toJSONString(
                responseData,
                SerializerFeature.PrettyFormat
        ));

        return JSON.toJSONString(responseData);
    }

    /**
     * 获取问题内容.
     */
    public String getQuestionContentService(FrontRequest requestData) {
        Map<String, String> param = new HashMap<>();
        param.put("questionid", requestData.getQuestionid());

        // 查询
        Map<String, String> questionContent = this.frontRepository.getQuestionContent(param);
        questionContent.put("read", questionContent.get("readtimes"));
        questionContent.put("like", questionContent.get("goodtimes"));
        FrontResponse responseData = new FrontResponse();
        responseData.setQuestioncontent(questionContent);
        log.info("返回的新闻内容数据为：\n{}", JSON.toJSONString(
                responseData,
                SerializerFeature.PrettyFormat
        ));
        // 阅读量
        int readnum = this.frontRepository.addQuestionReadNum(param);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 问题点赞.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addQuestionGoodtimes(FrontRequest requestData) {
        Map<String, String> param = new HashMap<>();
        param.put("questionid", requestData.getQuestionid());

        // 点赞
        int addgood = this.frontRepository.addQuestionGoodtimes(param);

        // 返回
        return JSON.toJSONString(addgood);
    }
}
