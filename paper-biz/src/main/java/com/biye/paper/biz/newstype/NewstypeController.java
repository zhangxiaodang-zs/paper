package com.biye.paper.biz.newstype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
 * 更新履历： V0.0.1 2020/02/25 张孝党 创建.
 */
@Slf4j
@RequestMapping("/back/news")
@RestController
@CrossOrigin(origins = "*")
public class NewstypeController {

    @Autowired
    private NewstypeService newstypeService;

    /**
     * 查询一览.
     */
    @RequestMapping("/type/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询新闻类型一览开始..................");

        log.info("请求参数为：{}", requestParam);
        NewstypeRequest requestData = JSON.parseObject(requestParam, new TypeReference<NewstypeRequest>() {
        });

        // 查询
        String responseData = this.newstypeService.queryService(requestData);

        log.info("查询新闻类型一览结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 添加新闻类型.
     */
    @RequestMapping("/type/add")
    public String add(@RequestBody String requestParam) {
        log.info("新增新闻类型开始..................");

        log.info("请求参数为：{}", requestParam);
        NewstypeRequest requestData = JSON.parseObject(requestParam, new TypeReference<NewstypeRequest>() {
        });
        String addData = "";
        addData = this.newstypeService.addService(requestData);
        return addData;
    }

    /**
     * 编辑新闻类型.
     */
    @RequestMapping("/type/edit")
    public String edit(@RequestBody String requestParam) {
        log.info("编辑新闻类型开始..................");

        log.info("请求参数为：{}", requestParam);
        NewstypeRequest requestData = JSON.parseObject(requestParam, new TypeReference<NewstypeRequest>() {
        });

        String edieData = "";
        edieData = this.newstypeService.editService(requestData);

        return edieData;
    }

    /**
     * 删除.
     */
    @RequestMapping("/type/delete")
    public String del(@RequestBody String requestParam) {
        log.info("删除新闻类型开始..................");

        log.info("请求参数为：{}", requestParam);
        NewstypeRequest requestData = JSON.parseObject(requestParam, new TypeReference<NewstypeRequest>() {
        });

        // 删除
        String delData = this.newstypeService.delService(requestData);

        return delData;
    }
}
