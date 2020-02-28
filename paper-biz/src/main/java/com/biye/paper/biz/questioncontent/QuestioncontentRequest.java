package com.biye.paper.biz.questioncontent;

import com.biye.paper.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestioncontentRequest extends BaseRequest {

    // 类型
    private String questiontype = "";

    // 类型id
    private String questiontypeid = "";

    // 问题id
    private String questionid = "";

    // 列表
    private List<String> questionlist;

    // 删除列表
    private List<String> questionidlist;

    // 问题内容
    private String content = "";

    // 概要
    private String summary = "";

    // 标题
    private String title = "";
}
