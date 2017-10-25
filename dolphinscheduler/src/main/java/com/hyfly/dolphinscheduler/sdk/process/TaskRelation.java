package com.hyfly.dolphinscheduler.sdk.process;

import com.alibaba.fastjson2.JSONObject;
import com.hyfly.dolphinscheduler.sdk.remote.RequestHttpEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskRelation {

    private String name = "";

    private Long preTaskCode = 0L;

    private Integer preTaskVersion = 0;

    private Long postTaskCode;

    private Integer postTaskVersion = 0;

    private Integer conditionType = 0;

    private Integer conditionParams;

    /**
     * must rewrite,then {@link RequestHttpEntity#bodyToMap()} can transfer object to json string
     *
     * @return object json string
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
