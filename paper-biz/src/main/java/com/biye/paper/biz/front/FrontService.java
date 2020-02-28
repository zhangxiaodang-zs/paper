package com.biye.paper.biz.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        }

        // 查询
        List<Map<String, String>> newsList = this.frontRepository.getNewsList(param);
        // 总条数
        int cnt = this.frontRepository.getNewsCnt(param);

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

        FrontResponse responseData = new FrontResponse();
        responseData.setNewscontent(newsContent);
        log.info("返回的新闻内容数据为：\n{}", JSON.toJSONString(
                responseData,
                SerializerFeature.PrettyFormat
        ));

        // 返回
        return JSON.toJSONString(responseData);
    }
}
