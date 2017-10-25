package com.hyfly.dolphinscheduler.sdk.project;

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

import java.util.List;
import java.util.Optional;

/**
 * operator for operate project
 */
@Slf4j
public class ProjectOperator extends AbstractOperator {

    public ProjectOperator(
            String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
        super(dolphinAddress, token, dolphinsRestTemplate);
    }

    /**
     * create project, api:/dolphinscheduler/projects
     *
     * @param projectCreateParam create project param
     * @return project info
     */
    public ProjectInfoResp create(ProjectCreateParam projectCreateParam) {
        String url = dolphinAddress + "/projects";
        try {
            HttpRestResult<ProjectInfoResp> result =
                    dolphinsRestTemplate.postForm(
                            url, getHeader(), projectCreateParam, ProjectInfoResp.class);
            if (result.getSuccess()) {
                return result.getData();
            } else {
                log.error("create project response:{}", result);
                throw new DolphinException("create dolphin scheduler project fail");
            }
        } catch (Exception e) {
            throw new DolphinException("create dolphin scheduler project fail", e);
        }
    }

    /**
     * update project, api：/dolphinscheduler/projects/{code}
     *
     * @param projectUpdateParam update project param
     * @return true for success,otherwise false
     */
    public ProjectInfoResp update(ProjectUpdateParam projectUpdateParam) {
        String url = dolphinAddress + "/projects/" + projectUpdateParam.getProjectCode();
        try {
            HttpRestResult<ProjectInfoResp> result =
                    dolphinsRestTemplate.putForm(url, getHeader(), projectUpdateParam, ProjectInfoResp.class);
            if (result.getSuccess()) {
                return result.getData();
            } else {
                log.error("update project response:{}", result);
                throw new DolphinException("update dolphin scheduler project fail");
            }
        } catch (Exception e) {
            throw new DolphinException("update dolphin scheduler project fail", e);
        }
    }

    /**
     * delete dolphin scheduler project
     *
     * @param projectCode dolphin scheduler project code
     * @return true for success,otherwise false
     */
    public Boolean delete(Long projectCode) {
        String url = dolphinAddress + "/projects/" + projectCode;
        try {
            HttpRestResult<String> result =
                    dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
            log.info("delete project response:{}", result);
            return result.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("delete dolphin scheduler project fail", e);
        }
    }

    /**
     * page query project list ，api:/dolphinscheduler/projects
     *
     * @param page        page number
     * @param size        page size
     * @param projectName project's name query criteria
     * @return {@link List<ProjectInfoResp>}
     */
    public PageInfo<ProjectInfoResp> page(Integer page, Integer size, String projectName) {

        page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
        size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);

        String url = dolphinAddress + "/projects";
        Query query =
                new Query()
                        .addParam("pageNo", String.valueOf(page))
                        .addParam("pageSize", String.valueOf(size))
                        .addParam("searchVal", projectName)
                        .build();
        try {
            HttpRestResult<JsonNode> restResult =
                    dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);

            return JSONObject.parseObject(
                    restResult.getData().toString(),
                    new TypeReference<>() {
                    });
        } catch (Exception e) {
            throw new DolphinException("list dolphin scheduler project fail", e);
        }
    }
}
