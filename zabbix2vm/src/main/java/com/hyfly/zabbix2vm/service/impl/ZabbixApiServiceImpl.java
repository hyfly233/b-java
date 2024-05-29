package com.hyfly.zabbix2vm.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSONObject;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.hyfly.zabbix2vm.entity.bo.ZabbixHostDetailBo;
import com.hyfly.zabbix2vm.entity.bo.ZabbixInterfaceBo;
import com.hyfly.zabbix2vm.entity.bo.ZabbixItemBo;
import com.hyfly.zabbix2vm.entity.po.ZabbixConfigPo;
import com.hyfly.zabbix2vm.entity.po.ZabbixItemInfo;
import com.hyfly.zabbix2vm.entity.resp.ZabbixHostDetailResp;
import com.hyfly.zabbix2vm.exception.BaseBootException;
import com.hyfly.zabbix2vm.service.IZabbixApiService;
import com.hyfly.zabbix2vm.service.IZabbixConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ZabbixApiServiceImpl implements IZabbixApiService {

    @Autowired
    private RestTemplate rt;

    @Autowired
    private IZabbixConfigService zabbixConfigService;

    @Override
    public String userLogin(String url, String reqId, String username, String password) {
        try {
            JSONObject jo = this.getUerLoginReq(reqId, username, password);

            // 发送请求
            ResponseEntity<String> responseEntity = rt.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(jo.toJSONString(), this.getHeaders()), String.class);
            String body = responseEntity.getBody();

            // 解析返回结果
            String auth = null;
            if (body != null && !body.isEmpty()) {
                JSONObject jsonObject = JSONObject.parseObject(body);
                if (jsonObject != null && jsonObject.containsKey("result")) {
                    auth = jsonObject.getString("result");
                }
            }

            return auth;
        } catch (Exception e) {
            String msg = "调用 Zabbix API 登录失败, url: " + url + ", username: " + username;
            log.error(msg, e);
            throw new BaseBootException(msg, e);
        }
    }

    @Override
    public Boolean userLogout(String url, String reqId, String auth) {
        try {
            JSONObject jo = this.baseUerLogoutReq(reqId, auth);

            // 发送请求
            ResponseEntity<String> responseEntity = rt.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(jo.toJSONString(), this.getHeaders()), String.class);
            String body = responseEntity.getBody();

            // 解析返回结果
            Boolean logoutSuccess = null;
            if (body != null && !body.isEmpty()) {
                JSONObject jsonObject = JSONObject.parseObject(body);
                if (jsonObject != null && jsonObject.containsKey("result")) {
                    logoutSuccess = jsonObject.getBoolean("result");
                }
            }

            return logoutSuccess;
        } catch (Exception e) {
            String msg = "调用 Zabbix API 登出失败, url: " + url;
            log.error(msg, e);
            throw new BaseBootException(msg, e);
        }
    }

    @Override
    public List<ZabbixHostDetailBo> getHostDetailInfos(String url, String reqId, String auth) {
        try {
            JSONObject jo = this.getHostsWithInterfacesAndItemsReq(reqId, auth);

            // 发送请求
            ResponseEntity<String> responseEntity = rt.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(jo.toJSONString(), this.getHeaders()), String.class);
            String body = responseEntity.getBody();

            // 解析返回结果
            List<ZabbixHostDetailBo> data = null;
            if (body != null && !body.isEmpty()) {
                ZabbixHostDetailResp resp = JSONObject.parseObject(body, ZabbixHostDetailResp.class);

                if (resp != null) {
                    data = resp.getResult();
                }
            }

            return data;
        } catch (Exception e) {
            String msg = "调用 Zabbix API 获取 host interface item 信息失败, url: " + url;
            log.error(msg, e);
            throw new BaseBootException(msg, e);
        }
    }

    @Override
    public Map<String, List<ZabbixHostDetailBo>> getHostInfos() {
        Map<String, List<ZabbixHostDetailBo>> map = new HashMap<>();

        List<ZabbixConfigPo> zabbixSourceConfigs = zabbixConfigService.listAll();

        for (ZabbixConfigPo config : zabbixSourceConfigs) {

            String auth = null;
            String reqId = IdUtil.simpleUUID();

            String url = config.getUrl();
            String username = config.getAccount();
            String password = config.getPassword();

            if (StringUtils.isBlank(url) || StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                continue;
            }

            try {
                auth = this.userLogin(url, reqId + "_1", username, password);

                if (auth != null) {
                    List<ZabbixHostDetailBo> infos = this.getHostDetailInfos(url, reqId + "_2", auth);
                    map.put(url, infos);
                }

            } catch (BaseBootException e) {
                throw e;
            } catch (Exception e1) {
                String msg = "调用 Zabbix API 获取 host 信息失败, url: " + url + ", username: " + username;
                log.error(msg, e1);
                throw new BaseBootException(msg, e1);
            } finally {
                if (auth != null) {
                    this.userLogout(url, reqId + "_3", auth);
                }
            }
        }

        return map;
    }

    @Override
    public ZabbixItemInfo getItemInfoByItemId(String url, Long itemId) {

        // 将数据存入缓存中
        Map<String, List<ZabbixHostDetailBo>> infoMap = this.getHostInfos();

        List<ZabbixHostDetailBo> infos = infoMap.get(url);

        if (infos != null) {
            for (ZabbixHostDetailBo info : infos) {
                if (info.getAvailable() == 0) {
                    continue;
                }

                List<ZabbixInterfaceBo> interfaces = info.getInterfaces();
                ZabbixInterfaceBo interfaceBo = null;

                for (ZabbixInterfaceBo anInterface : interfaces) {
                    if (anInterface.getMain() && anInterface.getType() == 1) {
                        interfaceBo = anInterface;
                        break;
                    }
                }

                if (interfaceBo == null) {
                    continue;
                }

                List<ZabbixItemBo> items = info.getItems();


            }
        }




        return null;
    }

    // ----------- Header ------------
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(new ArrayList<MediaType>() {{
            add(MediaType.APPLICATION_JSON);
        }});
        return headers;
    }

    // ----------- Login ------------

    private JSONObject baseUerLoginReq(String reqId) {
        JSONObject jo = new JSONObject();

        jo.put("id", reqId);
        jo.put("jsonrpc", "2.0");
        jo.put("method", "user.login");

        return jo;
    }

    private JSONObject getUerLoginReq(String reqId, String username, String password) {
        JSONObject jo = this.baseUerLoginReq(reqId);

        jo.put("params", new JSONObject() {{
            put("user", username);
            put("password", password);
            put("userData", false);
        }});

        return jo;
    }

    // ----------- Logout ------------

    private JSONObject baseUerLogoutReq(String reqId, String auth) {
        JSONObject jo = new JSONObject();

        jo.put("id", reqId);
        jo.put("auth", auth);
        jo.put("jsonrpc", "2.0");
        jo.put("method", "user.logout");
        jo.put("params", Collections.emptyList());

        return jo;
    }

    // ----------- Host ------------

    private JSONObject baseHostGetReq(String reqId, String auth) {
        JSONObject jo = new JSONObject();

        jo.put("id", reqId);
        jo.put("auth", auth);
        jo.put("jsonrpc", "2.0");
        jo.put("method", "host.get");

        return jo;
    }

    private JSONObject getHostsWithInterfacesAndItemsReq(String reqId, String auth) {
        JSONObject jsonObject = this.baseHostGetReq(reqId, auth);

        jsonObject.put("params", new JSONObject() {{
            put("output", new String[]{"hostid", "host", "name", "available", "description"});
            put("selectInterfaces", new String[]{"interfaceid", "main", "type", "useip", "ip", "dns", "port"});
            put("selectItems", new String[]{"itemid", "name", "key_", "value_type", "units", "description"});
        }});

        return jsonObject;
    }

    private static Cache<Long, ZabbixItemInfo> getCaffeineCache() {
        return Caffeine.newBuilder()
                .initialCapacity(3_000)
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .expireAfterAccess(2, TimeUnit.MINUTES)
                .build();
    }
}
