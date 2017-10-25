package com.hyfly.dolphinscheduler.sdk.task;

import com.alibaba.fastjson2.JSONObject;
import com.hyfly.dolphinscheduler.sdk.remote.RequestHttpEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * used by shell,python,spark,flink.... task
 *
 * <p>used when define task
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResource {

    private Long id;

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
