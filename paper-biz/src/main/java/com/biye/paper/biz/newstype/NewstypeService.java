package com.biye.paper.biz.newstype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
