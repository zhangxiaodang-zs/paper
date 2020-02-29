package com.biye.paper.biz.front;

import com.biye.paper.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrontRequest extends BaseRequest {

    // 新闻类型id
    private String newstypeid = "";

    // 新闻ID
    private String newsid = "";

    // 1为相关文章列表；2为热点新闻列表
    private String newstype = "";

    // 问题类型id
    private String questiontypeid = "";

    // 问题ID
    private String questionid = "";
}
