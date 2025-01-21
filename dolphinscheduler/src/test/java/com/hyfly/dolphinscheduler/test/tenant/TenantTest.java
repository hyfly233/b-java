package com.hyfly.dolphinscheduler.test.tenant;

import com.hyfly.dolphinscheduler.DolphinSchedulerApplication;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.tenant.TenantDefineParam;
import com.hyfly.dolphinscheduler.sdk.tenant.TenantInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(classes = DolphinSchedulerApplication.class)
public class TenantTest {

    public static final String TENANT_CODE = "test_tenant";

    private final DolphinClient dolphinClient;

    public TenantTest(@Autowired DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Test
    public void testCreateTenant() {
        TenantDefineParam createParam = new TenantDefineParam();
        createParam.setTenantCode(TENANT_CODE).setDescription("create by dolphin scheduler api");
        TenantInfoResp tenantInfoResp = dolphinClient.opsForTenant().create(createParam);
        System.out.println(tenantInfoResp);
        Assert.assertEquals(TENANT_CODE, tenantInfoResp.getTenantCode());
    }

    @Test
    public void testUpdateTenant() {
        PageInfo<TenantInfoResp> pageInfo = dolphinClient.opsForTenant().page(null, null, TENANT_CODE);
        long tenantId = pageInfo.getTotalList().get(0).getId();
        TenantDefineParam updateParam = new TenantDefineParam();
        updateParam.setTenantCode(TENANT_CODE).setDescription("update by dolphin scheduler");
        Assert.assertTrue(dolphinClient.opsForTenant().update(tenantId, updateParam));
    }

    @Test
    public void testPageTenant() {
        PageInfo<TenantInfoResp> pageInfo = dolphinClient.opsForTenant().page(null, null, "");
        List<TenantInfoResp> page = pageInfo.getTotalList();
        page.forEach(System.out::println);
    }

    @Test
    public void testDeleteTenant() {
        PageInfo<TenantInfoResp> pageInfo = dolphinClient.opsForTenant().page(null, null, TENANT_CODE);
        long tenantId = pageInfo.getTotalList().get(0).getId();
        Assert.assertTrue(dolphinClient.opsForTenant().delete(tenantId));
    }
}
