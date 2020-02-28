package com.biye.paper.biz.questioncontent;

import com.biye.paper.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QuestioncontentResponse extends BaseResponse {

    private List<Map<String, String>> questionlist;
    private String questiontype = "";
    private String questiontypeid = "";
    private String title = "";
    private String summary = "";
    private String questionid = "";
    private String content = "";
}
