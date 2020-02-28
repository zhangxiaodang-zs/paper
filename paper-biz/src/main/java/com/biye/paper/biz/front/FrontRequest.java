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

}
