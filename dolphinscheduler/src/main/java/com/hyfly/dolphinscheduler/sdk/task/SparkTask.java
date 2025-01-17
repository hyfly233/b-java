package com.hyfly.dolphinscheduler.sdk.task;

import com.hyfly.dolphinscheduler.sdk.process.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * reference: org.apache.dolphinscheduler.plugin.task.spark.SparkParameters
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SparkTask extends AbstractTask {

    private String appName;
    private ResourceInfo mainJar;
    private String mainClass;
    private String mainArgs;

    /**
     * optional value:cluster,client,local
     */
    private String deployMode;

    // spark task resource
    private Integer driverCores;
    private String driverMemory;
    private Integer numExecutors;
    private Integer executorCores;
    private String executorMemory;

    private String others;

    /**
     * yarn queue
     */
    private String queue;

    /**
     * optional value:JAVA,SCALA,PYTHON,SQL
     */
    private String programType;

    /**
     * spark sql script if programType is SQL
     */
    private String rawScript;

    /**
     * optional value:SPARK2,SPARK1
     */
    private String sparkVersion;

    private List<Parameter> localParams = Collections.emptyList();

    private List<ResourceInfo> resourceList = Collections.emptyList();

    public static SparkTask newV2Instance() {
        return new SparkTask().setSparkVersion("SPARK2");
    }

    public SparkTask inClientMode() {
        this.deployMode = "client";
        return this;
    }

    public SparkTask inClusterMode() {
        this.deployMode = "cluster";
        return this;
    }

    @Override
    public String getTaskType() {
        return "SPARK";
    }
}
