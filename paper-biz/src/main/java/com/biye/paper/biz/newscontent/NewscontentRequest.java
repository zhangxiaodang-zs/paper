package com.biye.paper.biz.newscontent;

import com.biye.paper.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewscontentRequest extends BaseRequest {

    // 类型
    private String newstype = "";

    // 列表
    private String newslist = "";


}
