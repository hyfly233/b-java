package com.hyfly.dolphinscheduler.sdk.remote.request;

import com.hyfly.dolphinscheduler.sdk.remote.RequestHttpEntity;
import com.hyfly.dolphinscheduler.sdk.remote.response.HttpClientResponse;

import java.io.Closeable;
import java.net.URI;

public interface HttpClientRequest extends Closeable {

    HttpClientResponse execute(URI uri, String httpMethod, RequestHttpEntity requestHttpEntity)
            throws Exception;
}
