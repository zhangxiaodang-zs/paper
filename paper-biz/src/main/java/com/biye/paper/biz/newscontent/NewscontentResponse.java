package com.biye.paper.biz.newscontent;

import com.biye.paper.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class NewscontentResponse extends BaseResponse {

    private List<Map<String, String>> newslist;
    // 类型
    private String newstype = "";
    // 类型id
    private String newstypeid = "";
    // 标题
    private String title = "";
    // 内容
    private String content = "";
    // 摘要
    private String summary = "";
    // 区分新增编辑
    private String edittype = "";
    // 略缩图地址
    private String newsurl = "";
    // 略缩图地址
    private String usrc = "";

    // 新闻id
    private String newsid = "";
}
