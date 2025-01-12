package com.hyfly.dolphinscheduler.sdk.remote;

import com.google.common.base.Strings;
import org.apache.http.client.methods.*;

public enum BaseHttpMethod {
    GET(com.hyfly.dolphinscheduler.sdk.remote.HttpMethod.GET) {
        @Override
        protected HttpRequestBase createRequest(String url) {
            return new HttpGet(url);
        }
    },

    POST(com.hyfly.dolphinscheduler.sdk.remote.HttpMethod.POST) {
        @Override
        protected HttpRequestBase createRequest(String url) {
            return new HttpPost(url);
        }
    },

    PUT(com.hyfly.dolphinscheduler.sdk.remote.HttpMethod.PUT) {
        @Override
        protected HttpRequestBase createRequest(String url) {
            return new HttpPut(url);
        }
    },

    PATCH(com.hyfly.dolphinscheduler.sdk.remote.HttpMethod.PATCH) {
        @Override
        protected HttpRequestBase createRequest(String url) {
            return new HttpPatch(url);
        }
    },

    DELETE(HttpMethod.DELETE) {
        @Override
        protected HttpRequestBase createRequest(String url) {
            return new HttpDelete(url);
        }
    };

    private final String name;

    BaseHttpMethod(String name) {
        this.name = name;
    }

    /**
     * get base http method by name
     *
     * @param name
     * @return
     */
    public static BaseHttpMethod of(String name) {
        if (!Strings.isNullOrEmpty(name)) {
            for (BaseHttpMethod method : BaseHttpMethod.values()) {
                if (name.equalsIgnoreCase(method.name)) {
                    return method;
                }
            }
        }
        throw new IllegalArgumentException("Unsupported http method : " + name);
    }

    public HttpRequestBase init(String url) {
        return createRequest(url);
    }

    protected HttpRequestBase createRequest(String url) {
        throw new UnsupportedOperationException();
    }
}
