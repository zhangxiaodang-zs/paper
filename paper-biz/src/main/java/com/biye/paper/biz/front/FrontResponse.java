package com.biye.paper.biz.front;

import com.biye.paper.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FrontResponse extends BaseResponse {

    // 新闻类型一览
    private List<Map<String, String>> newslist;

    // 新闻内容一览
    private List<Map<String, String>> newscontentlist;

    // 常见问题类型一览
    private List<Map<String, String>> quelist;

    // 新闻内容
    private Map<String, String> newscontent;
}
