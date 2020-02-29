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

    /**
     * 获取新闻详细内容.
     */
    @RequestMapping("/news/content")
    public String getNewsContent(@RequestBody String requestParam) {
        log.info("获取新闻内容开始.........................");

        log.info("请求参数为：{}", requestParam);
        FrontRequest requestData = JSON.parseObject(requestParam, new TypeReference<FrontRequest>() {
        });

        // 查询
        String responseData = this.frontService.getNewsContentService(requestData);

        log.info("获取新闻内容结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 获取相关文章和热点新闻列表.
     */
    @RequestMapping("/news/newshot")
    public String getNewsshotList(@RequestBody String requestParam) {
        log.info("获取相关文章和热点新闻列表开始.........................");

        log.info("请求参数为：{}", requestParam);
        FrontRequest requestData = JSON.parseObject(requestParam, new TypeReference<FrontRequest>() {
        });

        //查询
        String responseData = "";
        if(requestData.getNewstype().equals("1")){
            //问题
            requestData.setNewstype("1");
            responseData = this.frontService.getQuestionListService(requestData);
        }else if(requestData.getNewstype().equals("2")){
            //新闻
            requestData.setNewstype("2");
            responseData = this.frontService.getNewsListService(requestData);
        }

        log.info("获取新闻列表结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 新闻点赞.
     */
    @RequestMapping("/news/infolike")
    public String addNewsGoodtimes(@RequestBody String requestParam) {
        log.info("点赞开始.........................");

        log.info("请求参数为：{}", requestParam);
        FrontRequest requestData = JSON.parseObject(requestParam, new TypeReference<FrontRequest>() {
        });

        // 查询
        String responseData = this.frontService.addNewsGoodtimes(requestData);

        log.info("点赞开始结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }
}
