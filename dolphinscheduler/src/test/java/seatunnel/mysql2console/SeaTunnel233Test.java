package seatunnel.mysql2console;

import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.remote.DolphinsRestTemplate;
import com.hyfly.dolphinscheduler.sdk.remote.request.DefaultHttpClientRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.RequestContent;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * SeaTunnel 版本 2.3.3
 */
@SpringBootTest
public class SeaTunnel233Test {

    @Value("${ds.token}")
    private String token;

    @Value("${ds.address}")
    private String dolphinAddress;

    private DolphinClient dolphinClient;

    public SeaTunnel233Test() {
        DolphinsRestTemplate restTemplate = new DolphinsRestTemplate(
                new DefaultHttpClientRequest(
                        HttpClients.custom()
                                .addInterceptorLast(new RequestContent(true))
                                .setDefaultRequestConfig(RequestConfig.custom().build())
                                .build(),
                        RequestConfig.custom().build()));

        dolphinClient = new DolphinClient(token, dolphinAddress, restTemplate);
    }

    @BeforeEach
    void getChartData() {

    }

}
