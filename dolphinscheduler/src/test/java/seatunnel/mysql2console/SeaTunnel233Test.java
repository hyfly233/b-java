package seatunnel.mysql2console;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.project.ProjectInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SeaTunnel 版本 2.3.3
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SeaTunnel233Test {

    private final DolphinClient dolphinClient;

    public SeaTunnel233Test(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Test
    void listProject() {
        PageInfo<ProjectInfoResp> page = dolphinClient.opsForProject().page(null, null, null);

        if (page != null) {
            log.info("page size : {}", page.getPageSize());
            log.info("page data : {}", page.getTotalList());
        }
    }
}
