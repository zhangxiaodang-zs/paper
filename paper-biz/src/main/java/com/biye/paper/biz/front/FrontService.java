package com.biye.paper.biz.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Service
public class FrontService {

    @Autowired
    private FrontRepository frontRepository;

    /**
     * 获取二级菜单.
     */
    @RequestMapping("/menus")
    public String getSecondMenusService() {

        FrontResponse frontResponse = new FrontResponse();
        frontResponse.setNewslist(this.frontRepository.getNewMenu());
        frontResponse.setQuelist(this.frontRepository.getQueMenu());

        log.info("获取二级菜单返回的数据为:\n{}", JSON.toJSONString(frontResponse,
                SerializerFeature.PrettyFormat
        ));

        return JSON.toJSONString(frontResponse);
    }
}
