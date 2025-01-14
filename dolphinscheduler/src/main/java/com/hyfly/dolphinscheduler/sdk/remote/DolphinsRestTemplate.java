package com.hyfly.dolphinscheduler.sdk.remote;

import com.google.common.net.MediaType;
import com.hyfly.dolphinscheduler.sdk.remote.handler.ResponseHandler;
import com.hyfly.dolphinscheduler.sdk.remote.request.HttpClientRequest;
import com.hyfly.dolphinscheduler.sdk.remote.response.HttpClientResponse;
import com.hyfly.dolphinscheduler.sdk.util.HttpUtils;
import org.apache.http.entity.ContentType;

import java.net.URI;

/**
 * dolphin scheduler sdk custom rest template,which can support different http client
 */
public class DolphinsRestTemplate {

    private final HttpClientRequest requestClient;

    public DolphinsRestTemplate(HttpClientRequest requestClient) {
        this.requestClient = requestClient;
    }

    /**
     * http get url request are expanded using the given query
     *
     * @param url
     * @param header
     * @param query
     * @param responseType
     * @param <T>
     * @return {@link HttpRestResult}
     * @throws Exception
     */
    public <T> HttpRestResult<T> get(String url, Header header, Query query, Class<T> responseType)
            throws Exception {
        return execute(url, HttpMethod.GET, new RequestHttpEntity(header, query), responseType);
    }

    /**
     * @param url
     * @param header
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> postForm(
            String url, Header header, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.FORM_DATA.toString()), body);
        return execute(url, HttpMethod.POST, requestHttpEntity, responseType);
    }

    /**
     * @param url
     * @param header
     * @param query
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> postForm(
            String url, Header header, Query query, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.FORM_DATA.toString()), query, body);
        return execute(url, HttpMethod.POST, requestHttpEntity, responseType);
    }

    /**
     * post form with file and common param
     *
     * @param url
     * @param header
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> postFileForm(
            String url, Header header, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(
                        header.setContentType(ContentType.MULTIPART_FORM_DATA.getMimeType()), body);
        return execute(url, HttpMethod.POST, requestHttpEntity, responseType);
    }

    /**
     * post request which parameter is json,Content-Type is MediaType.JSON_UTF_8
     *
     * @param url
     * @param header
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> postJson(
            String url, Header header, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.JSON_UTF_8.toString()), body);
        return execute(url, HttpMethod.POST, requestHttpEntity, responseType);
    }

    /**
     * post request which parameter is json,Content-Type is MediaType.JSON_UTF_8
     *
     * @param url
     * @param header
     * @param query
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> postJson(
            String url, Header header, Query query, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.JSON_UTF_8.toString()), query, body);
        return execute(url, HttpMethod.POST, requestHttpEntity, responseType);
    }

    /**
     * @param url
     * @param header
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> putForm(
            String url, Header header, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.FORM_DATA.toString()), body);
        return execute(url, HttpMethod.PUT, requestHttpEntity, responseType);
    }

    /**
     * @param url
     * @param header
     * @param query
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> putForm(
            String url, Header header, Query query, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.FORM_DATA.toString()), query, body);
        return execute(url, HttpMethod.PUT, requestHttpEntity, responseType);
    }

    /**
     * post request which parameter is json,Content-Type is MediaType.JSON_UTF_8
     *
     * @param url
     * @param header
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> putJson(
            String url, Header header, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.JSON_UTF_8.toString()), body);
        return execute(url, HttpMethod.PUT, requestHttpEntity, responseType);
    }

    /**
     * post request which parameter is json,Content-Type is MediaType.JSON_UTF_8
     *
     * @param url
     * @param header
     * @param query
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> putJson(
            String url, Header header, Query query, Object body, Class<T> responseType) throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.JSON_UTF_8.toString()), query, body);
        return execute(url, HttpMethod.PUT, requestHttpEntity, responseType);
    }

    /**
     * @param url
     * @param header
     * @param responseType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> HttpRestResult<T> delete(String url, Header header, Query query, Class<T> responseType)
            throws Exception {
        RequestHttpEntity requestHttpEntity =
                new RequestHttpEntity(header.setContentType(MediaType.FORM_DATA.toString()), query);
        return execute(url, HttpMethod.DELETE, requestHttpEntity, responseType);
    }

    private <T> HttpRestResult<T> execute(
            String url, String httpMethod, RequestHttpEntity requestEntity, Class<T> responseType)
            throws Exception {
        URI uri = HttpUtils.buildUri(url, requestEntity.getQuery());

        ResponseHandler<T> responseHandler = new ResponseHandler<>();
        responseHandler.setResponseType(responseType);
        HttpClientResponse response = null;
        try {
            response = this.requestClient.execute(uri, httpMethod, requestEntity);
            return responseHandler.handle(response);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
