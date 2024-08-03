package com.hyfly.doristest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("example_tbl")
@Accessors(chain = true)
public class ExampleTbl {

    private Long userId;

    private String date;

    private String city;

    private Integer age;

    private Integer sex;

    private String lastVisitDate;

    private Long cost;

    private Integer maxDwellTime;

    private Integer minDwellTime;
}
