package com.biye.paper.biz.questiontype;

import com.biye.paper.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestiontypeRequest extends BaseRequest {

    // 类型
    private String questiontype = "";

    // 列表
    private String questiontypelist = "";

    // 类型id
    private String questiontypeid = "";

    // 删除列表
    private List<String> questiontypeidlist;
}
