package com.hyfly.dolphinscheduler.sdk.task;

import com.hyfly.dolphinscheduler.sdk.model.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * org.apache.dolphinscheduler.plugin.task.seatunnel.SeatunnelParameters
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SeatunnelTask extends AbstractTask {

    public List<Property> localParams;

    private String startupScript;

    private Boolean useCustom;

    private String rawScript;

    /**
     * resource list
     */
    private List<ResourceInfo> resourceList;

    private String deployMode;

    @Override
    public String getTaskType() {
        return "SEATUNNEL";
    }
}
