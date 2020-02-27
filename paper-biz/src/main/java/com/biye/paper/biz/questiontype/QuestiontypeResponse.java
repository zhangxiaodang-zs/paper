package com.biye.paper.biz.questiontype;

import com.biye.paper.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QuestiontypeResponse extends BaseResponse {

    private List<Map<String, String>> questiontypelist;
}
