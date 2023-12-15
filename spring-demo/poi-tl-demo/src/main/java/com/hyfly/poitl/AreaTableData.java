package com.hyfly.poitl;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AreaTableData {

    private Integer no;

    private String name;

    private String desc;

    private Integer num;
}
