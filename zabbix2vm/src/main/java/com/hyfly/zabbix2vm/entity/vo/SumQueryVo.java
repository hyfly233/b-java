package com.hyfly.zabbix2vm.entity.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SumQueryVo {

    @JSONField(name = "sum")
    private String sum;

    @JSONField(name = "values")
    List<List<String>> values;
}
