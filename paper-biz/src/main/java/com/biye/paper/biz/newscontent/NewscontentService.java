package com.biye.paper.biz.newscontent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.biye.paper.biz.newstype.NewstypeRequest;
import com.biye.paper.biz.newstype.NewstypeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NewscontentService {

    @Autowired
    private NewscontentRepository newscontentRepository;

    /**
     * 查询一览.
     */
    public String queryService(NewscontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("newstype", requestData.getNewstype());

        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        }

        // 查询
        List<Map<String, String>> newscontentlist = this.newscontentRepository.getNewContentList(param);
        // 总条数
        int cnt = this.newscontentRepository.getNewContentCnt(param);

        NewscontentResponse response = new NewscontentResponse();
        response.setNewslist(newscontentlist);
        response.setTotalcount(cnt);

        log.info("查询新闻内容一览返回的数据为：{}\n", JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 删除.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delService(NewscontentRequest requestData) {
        // 删除
        int result = 0;
        NewscontentResponse response = new NewscontentResponse();
        //遍历删除多条
        for (String advertId : requestData.getNewsidlist()) {
            Map<String, String> param = new HashMap<>();
            param.put("id", advertId);
            result = this.newscontentRepository.delNewContent(param);
            if (result == 1) {
                log.info("删除新闻内容成功..................");
            } else {
                log.info("删除新闻内容失败..................");
            }
        }

        // 返回
        return JSON.toJSONString(response);
    }
}
