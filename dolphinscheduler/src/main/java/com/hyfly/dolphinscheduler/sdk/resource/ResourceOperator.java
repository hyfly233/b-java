package com.hyfly.dolphinscheduler.sdk.resource;

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

// TODO support upload file
@Slf4j
public class ResourceOperator extends AbstractOperator {

    public ResourceOperator(
            String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
        super(dolphinAddress, token, dolphinsRestTemplate);
    }

    /**
     * page query resource list
     *
     * @param pid      pid
     * @param fileName file name
     * @return {@link List <ResourceQueryRes>}
     */
    public PageInfo<ResourceQueryRes> page(Integer page, Integer size, String pid, String fileName) {

        page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
        size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);

        String url = dolphinAddress + "/resources";
        Query query =
                new Query()
                        .addParam("type", DolphinClientConstant.Resource.TYPE_FILE)
                        .addParam("pageNo", String.valueOf(page))
                        .addParam("pageSize", String.valueOf(size))
                        .addParam("searchVal", fileName)
                        .addParam("fullName", "")
                        .addParam("tenantCode", "")
                        .addParam("id", pid);
        try {
            HttpRestResult<JsonNode> restResult =
                    dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);

            return JSONObject.parseObject(
                    restResult.getData().toString(),
                    new TypeReference<>() {
                    });
        } catch (Exception e) {
            throw new DolphinException("list dolphin scheduler resource fail", e);
        }
    }

    /**
     * upload resource
     *
     * @param resourceUploadParam upload file param
     * @return resource info
     */
    public Boolean upload(ResourceUploadParam resourceUploadParam) {
        String url = dolphinAddress + "/resources";

        try {
            HttpRestResult<String> restResult =
                    dolphinsRestTemplate.postFileForm(url, getHeader(), resourceUploadParam, String.class);
            return restResult.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("upload dolphin scheduler resource fail.", e);
        }
    }

    /**
     * online create resource/file
     *
     * @param resourceCreateParam online create file param
     * @return true for success,otherwise false
     */
    public Boolean onlineCreate(ResourceCreateParam resourceCreateParam) {
        String url = dolphinAddress + "/resources/online-create";
        try {
            HttpRestResult<String> restResult =
                    dolphinsRestTemplate.postForm(url, getHeader(), resourceCreateParam, String.class);
            return restResult.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("online create resource fail", e);
        }
    }

    /**
     * online update resource/file
     *
     * @param resourceUpdateParam online update resource param
     * @return true for success,otherwise false
     */
    public Boolean onlineUpdate(ResourceUpdateParam resourceUpdateParam) {
        String url = dolphinAddress + "/resources/update-content";
        try {
            HttpRestResult<String> restResult =
                    dolphinsRestTemplate.putForm(url, getHeader(), resourceUpdateParam, String.class);
            return restResult.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("online update resource fail", e);
        }
    }

    /**
     * delete resource by id
     *
     * @param tenantCode tenantCode
     * @param fullName   fullName
     * @return
     */
    public Boolean delete(String tenantCode, String fullName) {
        String url = dolphinAddress + "/resources";
        Query query = new Query().addParam("tenantCode", tenantCode).addParam("fullName", fullName);
        try {
            HttpRestResult<String> restResult =
                    dolphinsRestTemplate.delete(url, getHeader(), query, String.class);
            return restResult.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("delete resource fail", e);
        }
    }
}
