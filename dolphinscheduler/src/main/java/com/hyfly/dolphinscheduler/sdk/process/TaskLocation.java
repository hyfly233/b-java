package com.hyfly.dolphinscheduler.sdk.process;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.hyfly.dolphinscheduler.sdk.remote.RequestHttpEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TaskLocation {

    @JSONField(name = "taskCode")
    private Long taskCode;

    @JSONField(name = "x")
    private int x;

    @JSONField(name = "y")
    private int y;

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
