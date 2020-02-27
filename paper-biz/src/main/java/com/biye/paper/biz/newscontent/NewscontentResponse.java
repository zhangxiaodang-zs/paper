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
}
