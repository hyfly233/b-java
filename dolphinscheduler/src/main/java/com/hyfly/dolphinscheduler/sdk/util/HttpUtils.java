package com.hyfly.dolphinscheduler.sdk.util;

import com.hyfly.dolphinscheduler.sdk.remote.Query;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpUtils {

    /**
     * build URI By url and query.
     *
     * @param url   url
     * @param query query param {@link Query}
     * @return {@link URI}
     */
    public static URI buildUri(String url, Query query) throws URISyntaxException {
        if (query != null && !query.isEmpty()) {
            url = url + "?" + query.toQueryUrl();
        }
        return new URI(url);
    }
}
