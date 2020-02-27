package com.biye.paper.biz.questiontype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.biye.paper.biz.newstype.NewstypeRepository;
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
public class QuestiontypeService {

    @Autowired
    private QuestiontypeRepository questiontypeRepository;

    /**
     * 查询一览.
     */
    public String queryService(QuestiontypeRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("questiontype", requestData.getQuestiontype());

        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        }

        // 查询
        List<Map<String, String>> questiontypelist = this.questiontypeRepository.getQuestionTypeList(param);
        // 总条数
        int cnt = this.questiontypeRepository.getQuestionTypeCnt(param);

        QuestiontypeResponse response = new QuestiontypeResponse();
        response.setQuestiontypelist(questiontypelist);
        response.setTotalcount(cnt);

        log.info("查询问题类型一览返回的数据为：{}\n", JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 准确查询.
     */
    public String queryServiceAccurate(QuestiontypeRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("questiontype", requestData.getQuestiontype());

        // 查询
        List<Map<String, String>> questiontypelist = this.questiontypeRepository.getQuestionTypeListAccurate(param);
        QuestiontypeResponse response = new QuestiontypeResponse();
        response.setQuestiontypelist(questiontypelist);

        log.info("根据类型查询的结果：{}\n", JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 新增.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addService(QuestiontypeRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("questiontype", requestData.getQuestiontype());

        //获取时间
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date dd = Calendar.getInstance().getTime();
        date = sdf.format(dd);
        param.put("time", date);
        //id
        param.put("id", BiyeCommonUtil.getUUid());
        // 插入
        // 先查询是否存在相同名称
        String responseData = this.queryServiceAccurate(requestData);
        QuestiontypeRequest inquireData = JSON.parseObject(responseData, new TypeReference<QuestiontypeRequest>() {
        });
        int addData = 0;
        QuestiontypeResponse response = new QuestiontypeResponse();
        if (inquireData.getQuestiontypelist().equals("[]")) {
            //表示查询结果为空 进行新增操作
            addData = this.questiontypeRepository.addQuestionType(param);
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
     * 编辑.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editService(QuestiontypeRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("questiontype", requestData.getQuestiontype());
        //获取时间
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date dd = Calendar.getInstance().getTime();
        date = sdf.format(dd);
        param.put("time", date);
        //id
        param.put("id", requestData.getQuestiontypeid());
        // 更新该id对应信息
        // 先查询是否存在相同名称
        String responseData = this.queryServiceAccurate(requestData);
        QuestiontypeRequest inquireData = JSON.parseObject(responseData, new TypeReference<QuestiontypeRequest>() {
        });
        int result = 0;
        QuestiontypeResponse response = new QuestiontypeResponse();
        if (inquireData.getQuestiontypelist().equals("[]")) {
            //表示查询结果为空 进行更新操作
            result = this.questiontypeRepository.editQuestionType(param);
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
     * 删除.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delService(QuestiontypeRequest requestData) {
        // 删除
        int result = 0;
        QuestiontypeResponse response = new QuestiontypeResponse();
        //遍历删除多条
        for (String advertId : requestData.getQuestiontypeidlist()) {
            Map<String, String> param = new HashMap<>();
            param.put("id", advertId);
            result = this.questiontypeRepository.delQuestionType(param);
            if (result == 1) {
                log.info("删除问题成功..................");
            } else {
                log.info("删除问题失败..................");
            }
        }

        // 返回
        return JSON.toJSONString(response);
    }
}
