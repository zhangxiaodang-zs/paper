package com.biye.paper.biz.questioncontent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.biye.paper.biz.newscontent.NewscontentRepository;
import com.biye.paper.biz.newscontent.NewscontentRequest;
import com.biye.paper.biz.newscontent.NewscontentResponse;
import com.biye.paper.biz.newscontent.NewscontentService;
import com.biye.paper.biz.newstype.NewstypeRequest;
import com.biye.paper.biz.newstype.NewstypeResponse;
import com.biye.paper.core.utils.BiyeCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class QuestioncontentService {

    @Autowired
    private QuestioncontentRepository questioncontentRepository;

    @Autowired
    private NewscontentService newscontentService;

    private String url = "/home/biye/src/paper_web/views/statics/";

    /**
     * 查询一览.
     */
    public String queryService(QuestioncontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("questiontype", requestData.getQuestiontype());
        param.put("questionid", requestData.getQuestionid());

        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        }

        // 查询
        List<Map<String, String>> questioncontentlist = this.questioncontentRepository.getQuestionContentList(param);
        // 总条数
        int cnt = this.questioncontentRepository.getQuestionContentCnt(param);

        QuestioncontentResponse response = new QuestioncontentResponse();
        response.setQuestionlist(questioncontentlist);
        response.setTotalcount(cnt);

        log.info("查询新闻内容一览返回的数据为：{}\n", JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 删除.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delService(QuestioncontentRequest requestData) {
        // 删除
        int result = 0;
        QuestioncontentResponse response = new QuestioncontentResponse();
        //遍历删除多条
        for (String advertId : requestData.getQuestionidlist()) {
            Map<String, String> param = new HashMap<>();
            param.put("id", advertId);
            result = this.questioncontentRepository.delQuestionContent(param);
            if (result == 1) {
                log.info("删除新闻内容成功..................");
                url+=requestData.getQuestionid()+".html";
                newscontentService.delHtml(url);
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
    public String queryDetailService(QuestioncontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("questionid", requestData.getQuestionid());

        // 查询
        QuestioncontentResponse questioncontent = this.questioncontentRepository.getQuestionContentDetail(param);

        log.info("查询问题内容详情返回的数据为：{}\n", JSON.toJSONString(questioncontent, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(questioncontent);
    }

    /**
     * 编辑.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editService(QuestioncontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("content", requestData.getContent());
        param.put("summary", requestData.getSummary());
        param.put("title", requestData.getTitle());
        param.put("questiontypeid", requestData.getQuestiontypeid());
        //获取时间
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date dd = Calendar.getInstance().getTime();
        date = sdf.format(dd);
        param.put("time", date);
        //id
        param.put("questionid", requestData.getQuestionid());
        // 更新该id对应信息
        // 先查询是否存在相同名称
        String responseData = this.queryService(requestData);
        QuestioncontentRequest inquireData = JSON.parseObject(responseData, new TypeReference<QuestioncontentRequest>() {
        });
        int result = 0;
        QuestioncontentResponse response = new QuestioncontentResponse();
        if (!inquireData.getQuestionlist().equals("[]")) {
            //表示查询结果为空 进行更新操作
            result = this.questioncontentRepository.editQuestionContent(param);
            if(result > 0){
                url = url+requestData.getQuestionid()+".html";
                newscontentService.writeHtml(url,requestData.getContent(),"YES");
                response.setHtmlurl(url);
            }
            log.info("更新结束..................");
            response.setCode("200");
        } else {
            log.info("已经存在相同类型..........");
            response.setCode("201");
        }

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 新增.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addService(QuestioncontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("content", requestData.getContent());
        param.put("summary", requestData.getSummary());
        param.put("title", requestData.getTitle());
        param.put("questiontypeid", requestData.getQuestiontypeid());

        //获取时间
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date dd = Calendar.getInstance().getTime();
        date = sdf.format(dd);
        param.put("time", date);
        //id
        param.put("questionid", BiyeCommonUtil.getUUid());
        // 插入
        // 先查询是否存在相同名称
        String responseData = this.queryServiceAccurate(requestData);
        QuestioncontentRequest inquireData = JSON.parseObject(responseData, new TypeReference<QuestioncontentRequest>() {
        });
        int addData = 0;
        QuestioncontentResponse response = new QuestioncontentResponse();
        if (inquireData.getQuestionlist().size()==0) {
            //表示查询结果为空 进行新增操作
            addData = this.questioncontentRepository.addQuestionContent(param);
            if(addData > 0){
                url = url+requestData.getQuestionid()+".html";
                newscontentService.writeHtml(url,requestData.getContent(),"YES");
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
    public String queryServiceAccurate(QuestioncontentRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("title", requestData.getTitle());

        // 查询
        List<Map<String, String>> list = this.questioncontentRepository.getQuestionContentListAccurate(param);
        QuestioncontentResponse response = new QuestioncontentResponse();
        response.setQuestionlist(list);

        log.info("根据title查询的结果：{}\n", JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(response);
    }
}
