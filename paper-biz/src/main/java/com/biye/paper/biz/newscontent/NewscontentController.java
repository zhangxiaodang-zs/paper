package com.biye.paper.biz.newscontent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.biye.paper.biz.newstype.NewstypeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright(C) ZhiSheng 2019.
 * <p>
 * web端功能信息操作controller.
 *
 * @author 张明亮 2020/02/25.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2020/02/27 门海峰 创建.
 */
@Slf4j
@RequestMapping("/back/newscontent")
@RestController
@CrossOrigin(origins = "*")
public class NewscontentController {

    @Autowired
    private NewscontentService newscontentService;

    /**
     * 查询一览.
     */
    @RequestMapping("/content/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询新闻内容一览开始..................");

        log.info("请求参数为：{}", requestParam);
        NewscontentRequest requestData = JSON.parseObject(requestParam, new TypeReference<NewscontentRequest>() {
        });

        // 查询
        String responseData = this.newscontentService.queryService(requestData);

        log.info("查询新闻内容一览结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 删除.
     */
    @RequestMapping("/content/delete")
    public String del(@RequestBody String requestParam) {
        log.info("删除新闻内容开始..................");

        log.info("请求参数为：{}", requestParam);
        NewscontentRequest requestData = JSON.parseObject(requestParam, new TypeReference<NewscontentRequest>() {
        });

        // 删除
        String delData = this.newscontentService.delService(requestData);

        return delData;
    }
}
