package com.biye.paper.biz.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.biye.paper.biz.newstype.NewstypeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private FrontService frontService;

    /**
     * 首页获取二级菜单.
     */
    @RequestMapping("/menus")
    public String getSecondMenus() {
        log.info("官网首页获取二级菜单开始.........................");

        // 查询
        String responseData = this.frontService.getSecondMenusService();

        log.info("官网首页获取二级菜单结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 获取新闻列表.
     */
    @RequestMapping("/news/list")
    public String getNewsList(@RequestBody String requestParam) {
        log.info("获取新闻列表开始.........................");

        log.info("请求参数为：{}", requestParam);
        FrontRequest requestData = JSON.parseObject(requestParam, new TypeReference<FrontRequest>() {
        });

        // 查询
        String responseData = this.frontService.getNewsListService(requestData);

        log.info("获取新闻列表结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }
}
