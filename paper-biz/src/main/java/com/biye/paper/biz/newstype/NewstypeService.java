package com.biye.paper.biz.newstype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.biye.paper.core.utils.BiyeCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class NewstypeService {

    @Autowired
    private NewstypeRepository newstypeRepository;

    /**
     * 查询一览.
     */
    public String queryService(NewstypeRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("newstype", requestData.getNewstype());

        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        }

        // 查询
        List<Map<String, String>> newstypelist = this.newstypeRepository.getNewTypeList(param);
        // 总条数
        int cnt = this.newstypeRepository.getNewTypeCnt(param);

        NewstypeResponse response = new NewstypeResponse();
        response.setNewstypelist(newstypelist);
        response.setTotalcount(cnt);

        log.info("查询新闻类型一览返回的数据为：{}\n", JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 准确查询.
     */
    public String queryServiceAccurate(NewstypeRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("newstype", requestData.getNewstype());

        // 查询
        List<Map<String, String>> newstypelist = this.newstypeRepository.getNewTypeListAccurate(param);
        NewstypeResponse response = new NewstypeResponse();
        response.setNewstypelist(newstypelist);

        log.info("根据类型查询的结果：{}\n", JSON.toJSONString(response, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 新增.
     */
    public String addService(NewstypeRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("newstype", requestData.getNewstype());

        //获取时间
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date dd  = Calendar.getInstance().getTime();
        date = sdf.format(dd);
        param.put("time", date);
        //id
        param.put("id", BiyeCommonUtil.getUUid());
        // 插入
        // 先查询是否存在相同名称
        String responseData = this.queryServiceAccurate(requestData);
        NewstypeRequest inquireData = JSON.parseObject(responseData, new TypeReference<NewstypeRequest>() {
        });
        int addData=0;
        NewstypeResponse response = new NewstypeResponse();
        if(inquireData.getNewstypelist().equals("[]")){
            //表示查询结果为空 进行新增操作
            addData = this.newstypeRepository.addNewType(param);
            log.info("插入结束..................");
            response.setCode("200");
        }else{
            log.info("已经存在相同类型..........");
            response.setCode("201");
        }

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 编辑.
     */
    public String editService(NewstypeRequest requestData) {

        Map<String, Object> param = new HashMap<>();
        param.put("newstype", requestData.getNewstype());
        //获取时间
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date dd  = Calendar.getInstance().getTime();
        date = sdf.format(dd);
        param.put("time", date);
        //id
        param.put("id", requestData.getNewstypeid());
        // 更新该id对应信息
        // 先查询是否存在相同名称
        String responseData = this.queryServiceAccurate(requestData);
        NewstypeRequest inquireData = JSON.parseObject(responseData, new TypeReference<NewstypeRequest>() {
        });
        int result=0;
        NewstypeResponse response = new NewstypeResponse();
        if(inquireData.getNewstypelist().equals("[]")){
            //表示查询结果为空 进行更新操作
            result = this.newstypeRepository.editNewType(param);
            log.info("更新结束..................");
            response.setCode("200");
        }else{
            log.info("已经存在相同类型..........");
            response.setCode("201");
        }

        // 返回
        return JSON.toJSONString(response);
    }

    /**
     * 删除.
     */
    public String delService(List<String> advertList) {
        // 删除
        int result=0;
        NewstypeResponse response = new NewstypeResponse();
        //遍历删除多条
        for (String advertId : advertList) {
            Map<String, String> param = new HashMap<>();
            param.put("id", advertId);
            result = this.newstypeRepository.delNewType(param);
            if(result==1){
                log.info("删除新闻成功..................");
            }else{
                log.info("删除新闻失败..................");
            }
        }

        // 返回
        return JSON.toJSONString(response);
    }
}
