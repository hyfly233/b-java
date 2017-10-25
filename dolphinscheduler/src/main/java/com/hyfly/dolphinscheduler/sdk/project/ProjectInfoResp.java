package com.hyfly.dolphinscheduler.sdk.project;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * create project response,copied from org.apache.dolphinscheduler.dao.entity.Project
 */
@Data
public class ProjectInfoResp {

    /**
     * id
     */
    @JSONField(name = "id")
    private int id;

    /**
     * user id
     */
    @JSONField(name = "userId")
    private int userId;

    /**
     * user name
     */
    @JSONField(name = "userName")
    private String userName;

    /**
     * project code
     */
    @JSONField(name = "code")
    private long code;

    /**
     * project name
     */
    @JSONField(name = "name")
    private String name;

    /**
     * project description
     */
    @JSONField(name = "description")
    private String description;

    /**
     * create time
     */
    @JSONField(name = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * update time
     */
    @JSONField(name = "updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * permission
     */
    @JSONField(name = "perm")
    private int perm;

    /**
     * process define count
     */
    @JSONField(name = "defCount")
    private int defCount;

    /**
     * process instance running count
     */
    @JSONField(name = "instRunningCount")
    private int instRunningCount;
}
