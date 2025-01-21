package com.hyfly.dolphinscheduler.test.resource;

import com.hyfly.dolphinscheduler.DolphinSchedulerApplication;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClientConstant;
import com.hyfly.dolphinscheduler.sdk.resource.ResourceCreateParam;
import com.hyfly.dolphinscheduler.sdk.resource.ResourceQueryRes;
import com.hyfly.dolphinscheduler.sdk.resource.ResourceUpdateParam;
import com.hyfly.dolphinscheduler.sdk.resource.ResourceUploadParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@Slf4j
@SpringBootTest(classes = DolphinSchedulerApplication.class)
public class ResourceTest {

    public static final String TENANT_CODE = "root";

    private final String fileName = "dophinsdk-create2";
    private final String suffix = "sh";
    private final String fullName =
            "file:/home/"
                    + TENANT_CODE
                    + "/ds/upload/"
                    + TENANT_CODE
                    + "/resources/"
                    + fileName
                    + "."
                    + suffix;

    private final DolphinClient dolphinClient;

    public ResourceTest(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Test
    public void testPage() {
        PageInfo<ResourceQueryRes> pageInfo = dolphinClient.opsForResource().page(null, null, DolphinClientConstant.Resource.DEFAULT_PID_FILE, "");
        List<ResourceQueryRes> list = pageInfo.getTotalList();

        list.forEach(System.out::println);
    }

    @Test
    public void testOnlineCreate() {
        ResourceCreateParam resourceCreateParam = new ResourceCreateParam();
        resourceCreateParam
                .setSuffix(suffix)
                .setFileName(fileName)
                .setContent("created by dolphin scheduler java sdk");
        Assert.assertTrue(dolphinClient.opsForResource().onlineCreate(resourceCreateParam));
    }

    @Test
    public void testOnlineUpdate() {
        ResourceUpdateParam resourceUpdateParam = new ResourceUpdateParam();
        resourceUpdateParam
                .setTenantCode(TENANT_CODE)
                .setFullName(fullName)
                .setContent("update by dolphin scheduler java sdk");
        Assert.assertTrue(dolphinClient.opsForResource().onlineUpdate(resourceUpdateParam));
    }

    @Test
    public void testUploadFile() {
        ResourceUploadParam resourceUploadParam = new ResourceUploadParam();
        resourceUploadParam
                .setName("test_upload.txt")
                .setDescription("upload by dolphin scheduler java sdk")
                .setFile(new File("/home/chen/Documents/test_upload.txt"));
        Assert.assertTrue(dolphinClient.opsForResource().upload(resourceUploadParam));
    }

    @Test
    public void delete() {
        Assert.assertTrue(dolphinClient.opsForResource().delete(TENANT_CODE, fullName));
    }
}
