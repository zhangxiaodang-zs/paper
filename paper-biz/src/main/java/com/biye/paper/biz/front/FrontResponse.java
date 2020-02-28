package com.biye.paper.biz.front;

import com.biye.paper.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FrontResponse extends BaseResponse {

    private List<Map<String, String>> newslist;

    private List<Map<String, String>> quelist;
}
