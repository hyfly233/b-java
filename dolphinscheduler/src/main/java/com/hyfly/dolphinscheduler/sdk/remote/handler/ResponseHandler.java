package com.hyfly.dolphinscheduler.sdk.remote.handler;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.hyfly.dolphinscheduler.sdk.remote.HttpRestResult;
import com.hyfly.dolphinscheduler.sdk.remote.TypeReferenceHttpResult;
import com.hyfly.dolphinscheduler.sdk.remote.response.HttpClientResponse;
import org.apache.http.HttpStatus;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ResponseHandler<T> {

    private Class<T> responseType;

    public final void setResponseType(Class<T> responseType) {
        this.responseType = responseType;
    }

    public final HttpRestResult<T> handle(HttpClientResponse response) throws Exception {
        if (HttpStatus.SC_BAD_REQUEST < response.getStatusCode()) {
            return handleError(response);
        }
        return convertResult(response, this.responseType);
    }

    private HttpRestResult<T> handleError(HttpClientResponse response) throws Exception {
        String message =
                CharStreams.toString(new InputStreamReader(response.getBody(), Charsets.UTF_8));
        return new HttpRestResult<>(response.getStatusCode(), message, null, false, true);
    }

    public HttpRestResult<T> convertResult(HttpClientResponse response, Class<T> responseType)
            throws Exception {
        InputStream is = response.getBody();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(is, new TypeReferenceHttpResult<>(responseType));

//        return JSON.parseObject(is, TypeReferenceHttpResult.class);
    }
}
