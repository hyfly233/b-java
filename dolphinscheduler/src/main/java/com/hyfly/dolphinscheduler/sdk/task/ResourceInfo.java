package com.hyfly.dolphinscheduler.sdk.task;

import com.alibaba.fastjson2.JSONObject;
import com.hyfly.dolphinscheduler.sdk.remote.RequestHttpEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * org.apache.dolphinscheduler.plugin.task.api.model.ResourceInfo
 * <p>
 *  used by shell,python,spark,flink.... task
 *
 * <p>used when define task
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceInfo {

    private String resourceName;

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
