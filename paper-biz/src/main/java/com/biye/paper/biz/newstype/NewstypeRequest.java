package com.biye.paper.biz.newstype;

import com.biye.paper.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewstypeRequest extends BaseRequest {

    // 类型
    private String newstype = "";
    // 列表
    private String newstypelist = "";
    // 类型id
    private String newstypeid = "";
    private String newstypeidlist = "";
}
