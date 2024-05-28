package com.hyfly.zabbix2vm.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.NumberUtil;
import com.hyfly.zabbix2vm.entity.bo.OpentsdbMetric;
import com.hyfly.zabbix2vm.entity.bo.ZabbixHistory;
import com.hyfly.zabbix2vm.entity.constant.ZabbixMetric;
import com.hyfly.zabbix2vm.entity.po.ZabbixItemInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DataTransform {

    public static OpentsdbMetric history2Metric(ZabbixHistory history) {
        if (history == null) {
            return null;
        }

        return OpentsdbMetric.builder()
                .value(history.getValue())
                .timestamp(history.getTimestamp())
                .build();
    }

    public static Map<String, String> getMetricTags(ZabbixItemInfo zabbixItemInfo) {

        Map<String, String> tags = new HashMap<>();

        if (zabbixItemInfo == null) {
            return tags;
        }

        String itemUnits = zabbixItemInfo.getItemUnits();

        tags.put(ZabbixMetric.HOST_IP, zabbixItemInfo.getIp());
        tags.put(ZabbixMetric.ITEM_NAME, zabbixItemInfo.getItemName());
        tags.put(ZabbixMetric.ITEM_KEY, zabbixItemInfo.getItemKey());
        tags.put(ZabbixMetric.ITEM_UNIT, itemUnits != null ? itemUnits : StringUtils.EMPTY);

        return tags;
    }

    public static String base64EncodeItemKey(String str) {
        if (str != null) {
            return Base64.encodeUrlSafe(str, CharsetUtil.UTF_8);
        }

        return null;
    }

    public static String base64DecodeItemKey(String str) {
        if (str != null) {
            return Base64.decodeStr(str, CharsetUtil.UTF_8);
        }

        return null;
    }

    public static Integer strToIntDefault0(String str) {
        int num = 0;

        if (NumberUtil.isInteger(str)) {
            num = Integer.parseInt(str);
        }

        return num;
    }
}
