package com.biye.paper.biz.front;

import com.biye.paper.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrontRequest extends BaseRequest {

    // 类型
    private String newstype = "";

    // 列表
    private String newstypelist = "";

    // 类型id
    private String newstypeid = "";

    // 删除列表
    private List<String> newstypeidlist;
}
