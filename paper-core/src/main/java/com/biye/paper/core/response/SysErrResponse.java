package com.biye.paper.core.response;

import com.alibaba.fastjson.JSONObject;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 异常返回信息.
 *
 * @author 张孝党 2019/08/26.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/08/26 张孝党 创建.
 */
public class SysErrResponse extends BaseResponse {

    /**
     * 构造方法,默认为异常.
     */
    public SysErrResponse(String errMsg) {
        super.setCode("9999");
        super.setMessage(errMsg);
    }

    /**
     * 构造方法,默认为异常.
     */
    public SysErrResponse(String errCode, String errMsg) {
        super.setCode(errCode);
        super.setMessage(errMsg);
    }

    /**
     * 转换为String型的json字符串.
     *
     * @return 字符串.
     */
    public String toJsonString() {
        JSONObject response = new JSONObject();
        response.put("code", super.getCode());
        response.put("message", super.getMessage());

        return response.toJSONString();
    }
}
