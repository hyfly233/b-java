package com.hyfly.dolphinscheduler.sdk.instance;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.AbstractOperator;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClientConstant;
import com.hyfly.dolphinscheduler.sdk.core.DolphinException;
import com.hyfly.dolphinscheduler.sdk.remote.DolphinsRestTemplate;
import com.hyfly.dolphinscheduler.sdk.remote.HttpRestResult;
import com.hyfly.dolphinscheduler.sdk.remote.Query;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class ProcessInstanceOperator extends AbstractOperator {

    public ProcessInstanceOperator(
            String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
        super(dolphinAddress, token, dolphinsRestTemplate);
    }

    /**
     * start/run process instance
     *
     * <p>api: /dolphinscheduler/projects/{projectCode}/executors/start-process-instance
     *
     * @param processInstanceCreateParam process instance create param
     * @return true for success,otherwise false
     */
    public Boolean start(Long projectCode, ProcessInstanceCreateParam processInstanceCreateParam) {
        String url = dolphinAddress + "/projects/" + projectCode + "/executors/start-process-instance";
        log.info("start process instance ,url:{}", url);
        try {
            HttpRestResult<JsonNode> restResult =
                    dolphinsRestTemplate.postForm(
                            url, getHeader(), processInstanceCreateParam, JsonNode.class);
            log.info("start process response:{}", restResult);
            return restResult.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("start dolphin scheduler process instance fail", e);
        }
    }

    /**
     * page query process's instance list
     *
     * @param page         page,default 1 while is null
     * @param size         size,default 10 while is null
     * @param projectCode  project code
     * @param workflowCode workflow id
     * @return
     */
    public PageInfo<ProcessInstanceQueryResp> page(
            Integer page, Integer size, Long projectCode, Long workflowCode) {
        page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
        size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);

        String url = dolphinAddress + "/projects/" + projectCode + "/process-instances";

        Query query = new Query();
        query
                .addParam("pageNo", String.valueOf(page))
                .addParam("pageSize", String.valueOf(size))
                .addParam("processDefineCode", String.valueOf(workflowCode));

        try {
            HttpRestResult<JsonNode> restResult =
                    dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);

            return JSONObject.parseObject(
                    restResult.getData().toString(),
                    new TypeReference<>() {
                    });
        } catch (Exception e) {
            throw new DolphinException("page dolphin scheduler process instance list fail", e);
        }
    }

    /**
     * repeat run dolphin scheduler workflow instance
     *
     * @param projectCode       project code
     * @param processInstanceId process instance id
     * @return true for success,otherwise false
     */
    public Boolean reRun(Long projectCode, Long processInstanceId) {
        log.info("repeat run workflow instance,id:{}", processInstanceId);
        return execute(projectCode, processInstanceId, DolphinClientConstant.ExecuteType.RE_RUN);
    }

    /**
     * stop dolphin scheduler workflow instance
     *
     * @param projectCode       project code
     * @param processInstanceId process instance id
     * @return true for success,otherwise false
     */
    public Boolean stop(Long projectCode, Long processInstanceId) {
        log.info("stop workflow instance,id:{}", processInstanceId);
        return execute(projectCode, processInstanceId, DolphinClientConstant.ExecuteType.STOP);
    }

    /**
     * pause dolphin scheduler workflow instance
     *
     * @param projectCode       project code
     * @param processInstanceId process instance id
     * @return true for success,otherwise false
     */
    public Boolean pause(Long projectCode, Long processInstanceId) {
        log.info("stop workflow instance,id:{}", processInstanceId);
        return execute(projectCode, processInstanceId, DolphinClientConstant.ExecuteType.PAUSE);
    }

    /**
     * execute dolphin scheduler workflow instance with custom execute type
     *
     * @param projectCode       project code
     * @param processInstanceId process instance id
     * @param executeType       {@link DolphinClientConstant.ExecuteType}
     * @return true for success,otherwise false
     */
    public Boolean execute(Long projectCode, Long processInstanceId, String executeType) {
        String url = dolphinAddress + "/projects/" + projectCode + "/executors/execute";
        ProcessInstanceRunParam reProcessInstanceRunParam =
                new ProcessInstanceRunParam()
                        .setProcessInstanceId(processInstanceId)
                        .setExecuteType(executeType);
        try {
            HttpRestResult<String> restResult =
                    dolphinsRestTemplate.postForm(url, getHeader(), reProcessInstanceRunParam, String.class);
            return restResult.getSuccess();
        } catch (Exception e) {
            throw new DolphinException(executeType + " dolphin scheduler process instance fail", e);
        }
    }

    public Boolean delete(Long projectCode, Long processInstanceId) {
        String url =
                dolphinAddress + "/projects/" + projectCode + "/process-instances/" + processInstanceId;
        try {
            HttpRestResult<String> restResult =
                    dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
            return restResult.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("delete dolphin scheduler process instance fail", e);
        }
    }
}
