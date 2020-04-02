package com.biye.paper.biz.newscontent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.biye.paper.biz.newstype.NewstypeRequest;
import com.biye.paper.biz.newstype.NewstypeResponse;
import com.biye.paper.biz.questioncontent.QuestioncontentRequest;
import com.biye.paper.biz.questioncontent.QuestioncontentResponse;
import com.biye.paper.core.utils.BiyeCommonUtil;
import com.biye.paper.core.utils.BiyeDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NewscontentService {

    @Autowired
    private NewscontentRepository newscontentRepository;

    private String url = "/home/biye/src/paper_web/views/statics/";
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
            if (result >0) {
                log.info("删除新闻内容成功..................");
                url+=requestData.getNewsid()+".html";
                delHtml(url);
            } else {
                log.info("删除新闻内容失败..................");
            }
        }

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 详情.
     */
    public String queryDetailService(NewscontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("newsid", requestData.getNewsid());

        // 查询
        NewscontentResponse newscontent = this.newscontentRepository.getNewsContentDetail(param);
        newscontent.setEdittype("edit");
        log.info("查询新闻内容详情返回的数据为：{}\n", JSON.toJSONString(newscontent, SerializerFeature.PrettyFormat));
        // 返回
        return JSON.toJSONString(newscontent);
    }

    /**
     * 编辑.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editService(NewscontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("newsurl", requestData.getNewsurl());
        param.put("title", requestData.getTitle());
        param.put("content", requestData.getContent());
        param.put("summary", requestData.getSummary());
        param.put("newstypeid", requestData.getNewstypeid());
        param.put("time", BiyeDateTimeUtil.getTimeformat());
        //id
        param.put("newsid", requestData.getNewsid());
        // 更新该id对应信息
        // 先查询是否存在相同名称
        String responseData = this.queryServiceAccurate(requestData);
//        NewscontentRequest inquireData = JSON.parseObject(responseData, new TypeReference<NewscontentRequest>() {
//        });
//        int result = 0;
//        NewstypeResponse response = new NewstypeResponse();
//        if (inquireData.getNewslist().size()==0) {
//            //表示查询结果为空 进行更新操作
//            result = this.newscontentRepository.editNewsContent(param);
//            log.info("更新结束..................");
//            response.setCode("200");
//        } else {
//            log.info("已经存在相同类型..........");
//            response.setCode("201");
//        }
        NewscontentResponse response = new NewscontentResponse();
        int result = this.newscontentRepository.editNewsContent(param);
        if(result > 0){
            log.info("url"+url);
            url = url+requestData.getNewsid()+".html";
            writeHtml(url,requestData.getContent());
            response.setHtmlurl(url);
        }
        log.info("更新结束..................");
        response.setCode("200");

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 新增.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addService(NewscontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("title", requestData.getTitle());
        param.put("content", requestData.getContent());
        param.put("summary", requestData.getSummary());
        param.put("newsurl", requestData.getNewsurl());
        param.put("newstypeid", requestData.getNewstypeid());

        //获取时间
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date dd = Calendar.getInstance().getTime();
        date = sdf.format(dd);
        param.put("time", date);
        //id
        param.put("newsid", BiyeCommonUtil.getUUid());
        // 插入
        // 先查询是否存在相同名称
        String responseData = this.queryServiceAccurate(requestData);
        NewscontentRequest inquireData = JSON.parseObject(responseData, new TypeReference<NewscontentRequest>() {
        });
        int addData = 0;
        NewscontentResponse response = new NewscontentResponse();
        if (inquireData.getNewslist().size() == 0) {
            //表示查询结果为空 进行新增操作
            addData = this.newscontentRepository.addNewsContent(param);
            if(addData > 0){
                log.info("url"+url);
                url = url+requestData.getNewsid()+".html";
                writeHtml(url,requestData.getContent());
                response.setHtmlurl(url);
            }
            log.info("插入结束..................");
            response.setCode("200");
        } else {
            log.info("已经存在相同类型..........");
            response.setCode("201");
        }

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 准确查询.
     */
    public String queryServiceAccurate(NewscontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("title", requestData.getTitle());

        // 查询
        List<Map<String, String>> list = this.newscontentRepository.getNewsContentListAccurate(param);
        NewscontentResponse response = new NewscontentResponse();
        response.setNewslist(list);

        log.info("根据title查询的结果：{}\n", JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(response);
    }

    public static void writeHtml(String filePath, String info) {
        PrintWriter pw = null;
        try {
            File writeFile = new File(filePath);
            boolean isExit = writeFile.exists();
            if (isExit != true) {
                writeFile.createNewFile();
            } else {
                writeFile.delete();
                writeFile.createNewFile();
            }
            pw = new PrintWriter(new FileOutputStream(filePath, true));
            pw.println(info);
            pw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            pw.close();
        }
    }

    public static void delHtml(String filePath) {
        PrintWriter pw = null;
        try {
            File writeFile = new File(filePath);
            boolean isExit = writeFile.exists();
            if (isExit != true) {

            } else {
                writeFile.delete();
            }
            pw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            pw.close();
        }
    }
}
