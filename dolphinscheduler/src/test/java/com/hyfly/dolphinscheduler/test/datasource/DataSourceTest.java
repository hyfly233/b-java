package com.hyfly.dolphinscheduler.test.datasource;

import com.hyfly.dolphinscheduler.DolphinSchedulerApplication;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.datasource.DataSourceCreateParam;
import com.hyfly.dolphinscheduler.sdk.datasource.DataSourceQueryResp;
import com.hyfly.dolphinscheduler.sdk.datasource.DataSourceUpdateParam;
import com.hyfly.dolphinscheduler.sdk.enums.DbTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest(classes = DolphinSchedulerApplication.class)
public class DataSourceTest {

    private final DolphinClient dolphinClient;

    public DataSourceTest(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    /**
     * create datasource
     */
    @Test
    public void createDataSource() {
        DataSourceCreateParam dataSourceCreateParam = new DataSourceCreateParam();
        Map<String, Object> map = new HashMap<>();
        map.put("useSSL", "false");
        dataSourceCreateParam
                .setUserName("root")
                .setPassword("xxxxxx") // use your own db info
                .setDatabase("test")
                .setPort("3306")
                .setName("ds-create-test2")
                .setType(DbTypeEnum.MYSQL.name())
                .setHost("localhost")
                .setOther(map);
        Assert.assertTrue(dolphinClient.opsForDataSource().create(dataSourceCreateParam));
    }

    /**
     * list all datasource
     */
    @Test
    public void listDataSource() {
        System.out.println(dolphinClient.opsForDataSource().list(null));
    }

    /**
     * update datasource
     */
    @Test
    public void updateDataSource() {
        PageInfo<DataSourceQueryResp> pageInfo = dolphinClient.opsForDataSource().list(null);
        List<DataSourceQueryResp> dataSources = pageInfo.getTotalList();
        DataSourceUpdateParam dataSourceUpdateParam = new DataSourceUpdateParam();
        dataSourceUpdateParam
                .setId(dataSources.get(0).getId()) // this id from create
                .setUserName("root")
                .setPassword("xxxxxx")
                .setDatabase("test")
                .setPort("3306")
                .setName("ds-create-test")
                .setNote("this note is generate by update api")
                .setType(DbTypeEnum.MYSQL.name())
                .setHost("localhost");
        Assert.assertTrue(dolphinClient.opsForDataSource().update(dataSourceUpdateParam));
    }

    @Test
    public void deleteDataSource() {
        PageInfo<DataSourceQueryResp> pageInfo = dolphinClient.opsForDataSource().list(null);
        List<DataSourceQueryResp> dataSources = pageInfo.getTotalList();
        Assert.assertTrue(dolphinClient.opsForDataSource().delete(dataSources.get(0).getId()));
    }
}
