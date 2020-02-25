package com.biye.paper.core.response;

import com.alibaba.fastjson.JSONObject;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 系统正常返回信息.
 *
 * @author 张孝党 2019/08/27.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/08/27 张孝党 创建.
 */

public class SysResponse {

    private String code = "200";

    private String message = "";

    public SysResponse(String message) {
        this.message = message;
    }

    /**
     * 转换为String型的json字符串.
     *
     * @return 字符串.
     */
    public String toJsonString() {

        // 返回报文
        JSONObject response = new JSONObject();
        response.put("code", this.code);
        response.put("message", this.message);

        return response.toJSONString();
    }
}
