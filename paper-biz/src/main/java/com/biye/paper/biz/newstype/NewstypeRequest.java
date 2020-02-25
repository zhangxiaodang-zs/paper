package com.biye.paper.biz.newstype;

import com.biye.paper.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewstypeRequest extends BaseRequest {

    // 类型
    private String newstype = "";
}
