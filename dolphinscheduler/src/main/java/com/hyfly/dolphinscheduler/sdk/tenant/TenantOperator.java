package com.hyfly.dolphinscheduler.sdk.tenant;

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
 * operator for tenant
 */
@Slf4j
public class TenantOperator extends AbstractOperator {

    public TenantOperator(
            String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
        super(dolphinAddress, token, dolphinsRestTemplate);
    }

    /**
     * create tenant, api: /dolphinscheduler/tenants
     *
     * @param tenantDefineParam create tenant param
     * @return tenant info
     */
    public TenantInfoResp create(com.hyfly.dolphinscheduler.sdk.tenant.TenantDefineParam tenantDefineParam) {
        String url = dolphinAddress + "/tenants";
        try {
            HttpRestResult<TenantInfoResp> result =
                    dolphinsRestTemplate.postForm(url, getHeader(), tenantDefineParam, TenantInfoResp.class);
            if (result.getSuccess()) {
                return result.getData();
            } else {
                log.error("create tenant response:{}", result);
                throw new DolphinException("create dolphin scheduler tenant fail");
            }
        } catch (Exception e) {
            throw new DolphinException("create dolphin scheduler tenant fail", e);
        }
    }

    /**
     * create tenant, api: /dolphinscheduler/tenants/{id}
     *
     * @param updateParam update tenant param
     * @return tenant info
     */
    public Boolean update(Long tenantId, TenantDefineParam updateParam) {
        String url = dolphinAddress + "/tenants/" + tenantId;
        try {
            HttpRestResult<String> result =
                    dolphinsRestTemplate.putForm(url, getHeader(), updateParam, String.class);
            log.info("update tenant response:{}", result);
            return result.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("update dolphin scheduler tenant fail", e);
        }
    }

    /**
     * delete dolphin scheduler tenant
     *
     * @param tenantId dolphin scheduler tenant id
     * @return true for success,otherwise false
     */
    public Boolean delete(Long tenantId) {
        String url = dolphinAddress + "/tenants/" + tenantId;
        try {
            HttpRestResult<String> result =
                    dolphinsRestTemplate.delete(url, getHeader(), null, String.class);
            log.info("delete tenant response:{}", result);
            return result.getSuccess();
        } catch (Exception e) {
            throw new DolphinException("delete dolphin scheduler tenant fail", e);
        }
    }

    /**
     * page query tenant list ，api:/dolphinscheduler/tenants
     *
     * @param page       page number
     * @param size       page size
     * @param tenantCode tenant code, such as root.
     * @return {@link List<TenantInfoResp>}
     */
    public PageInfo<TenantInfoResp> page(Integer page, Integer size, String tenantCode) {

        page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
        size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);

        String url = dolphinAddress + "/tenants";
        Query query =
                new Query()
                        .addParam("pageNo", String.valueOf(page))
                        .addParam("pageSize", String.valueOf(size))
                        .addParam("searchVal", tenantCode)
                        .build();
        try {
            HttpRestResult<JsonNode> restResult =
                    dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);

            return JSONObject.parseObject(
                    restResult.getData().toString(),
                    new TypeReference<>() {
                    });
        } catch (Exception e) {
            throw new DolphinException("list dolphin scheduler tenant fail", e);
        }
    }
}
