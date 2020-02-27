package com.biye.paper.biz.newscontent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.biye.paper.core.utils.BiyeCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;

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

    // 保存路径
    private String SAVE_PATH = "D:\\";

    // 返回的URL
    private String SAVE_URL = "";

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

    /**
     * 富文框上传图片.
     */
    @RequestMapping("/content/uploadfile")
    public String uploadUeditor(@RequestParam("upfile") MultipartFile file, HttpServletRequest request) {
        log.info("富文本框上传图片开始..................");

        JSONObject json = new JSONObject();

        try {
            InputStream inputStream = file.getInputStream();
            // 文件类型
            String fileType = file.getOriginalFilename().
                    substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            // 文件名
            String fileName = BiyeCommonUtil.getUUid() + "." + fileType;
            log.info("保存的文件名称为：[{}]", fileName);
            String fileFullPath = SAVE_PATH + fileName;
            file.transferTo(new File(fileFullPath));

            json.put("state", "SUCCESS");
            json.put("original", file.getOriginalFilename());
            json.put("size", file.getSize());
            // TODO
            // json.put("url", "http://www.biye.com.cn/back/img/" + fileName);
            json.put("url", "http://www.biye.com.cn/back/img/1.jpg");
            json.put("title", file.getOriginalFilename());
            json.put("type", file.getOriginalFilename().
                    substring(file.getOriginalFilename().lastIndexOf(".") + 1));
            json.put("code", "200");
        } catch (Exception ex) {
            log.error("富文本框上传图片时异常：\n{}", ex.getMessage());
            json.put("state", "FAILURE");
        }

        // 返回
        return json.toJSONString();
    }
}
