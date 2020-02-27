package com.biye.paper.biz.questiontype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.biye.paper.biz.newstype.NewstypeRequest;
import com.biye.paper.biz.newstype.NewstypeService;
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
@RequestMapping("/back/question")
@RestController
@CrossOrigin(origins = "*")
public class QuestiontypeController {

    @Autowired
    private QuestiontypeService questiontypeService;

    /**
     * 查询一览.
     */
    @RequestMapping("/type/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询问题类型一览开始..................");

        log.info("请求参数为：{}", requestParam);
        QuestiontypeRequest requestData = JSON.parseObject(requestParam, new TypeReference<QuestiontypeRequest>() {
        });

        // 查询
        String responseData = this.questiontypeService.queryService(requestData);

        log.info("查询问题类型一览结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 添加问题类型.
     */
    @RequestMapping("/type/add")
    public String add(@RequestBody String requestParam) {
        log.info("新增问题类型开始..................");

        log.info("请求参数为：{}", requestParam);
        QuestiontypeRequest requestData = JSON.parseObject(requestParam, new TypeReference<QuestiontypeRequest>() {
        });
        String addData = "";
        addData = this.questiontypeService.addService(requestData);
        return addData;
    }

    /**
     * 编辑问题类型.
     */
    @RequestMapping("/type/edit")
    public String edit(@RequestBody String requestParam) {
        log.info("编辑问题类型开始..................");

        log.info("请求参数为：{}", requestParam);
        QuestiontypeRequest requestData = JSON.parseObject(requestParam, new TypeReference<QuestiontypeRequest>() {
        });

        String edieData = "";
        edieData = this.questiontypeService.editService(requestData);

        return edieData;
    }

    /**
     * 删除.
     */
    @RequestMapping("/type/delete")
    public String del(@RequestBody String requestParam) {
        log.info("删除问题类型开始..................");

        log.info("请求参数为：{}", requestParam);
        QuestiontypeRequest requestData = JSON.parseObject(requestParam, new TypeReference<QuestiontypeRequest>() {
        });

        // 删除
        String delData = this.questiontypeService.delService(requestData);

        return delData;
    }
}
