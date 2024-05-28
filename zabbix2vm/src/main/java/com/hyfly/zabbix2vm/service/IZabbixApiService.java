package com.hyfly.zabbix2vm.service;



import com.hyfly.zabbix2vm.entity.bo.ZabbixHostDetailBo;
import com.hyfly.zabbix2vm.entity.po.ZabbixItemInfo;

import java.util.List;

public interface IZabbixApiService {

    /**
     * 登录 zabbix 获取 auth
     *
     * @param url      zabbix地址
     * @param reqId    请求id
     * @param username 用户名
     * @param password 密码
     * @return String auth
     */
    String userLogin(String url, String reqId, String username, String password);

    /**
     * 登出 zabbix
     *
     * @param url   zabbix地址
     * @param reqId 请求id
     * @param auth  auth
     * @return Boolean 是否登出成功
     */
    Boolean userLogout(String url, String reqId, String auth);

    /**
     * 获取主机详情信息列表、包括 interface、item 等
     *
     * @param url   zabbix地址
     * @param reqId 请求id
     * @param auth  auth
     * @return List<ZabbixHostDetailBo> 主机详情信息列表
     */
    List<ZabbixHostDetailBo> getHostDetailInfos(String url, String reqId, String auth);

    /**
     * 获取主机详情信息列表、包括 interface、item 等
     *
     * @return List<ZabbixHostDetailBo>
     */
    List<ZabbixHostDetailBo> getHostInfos();

    /**
     * 根据 itemId 获取 item 信息
     *
     * @param itemId zabbix item id
     * @return ZabbixItemInfo
     */
    ZabbixItemInfo getItemInfoByItemId(Long itemId);
}
