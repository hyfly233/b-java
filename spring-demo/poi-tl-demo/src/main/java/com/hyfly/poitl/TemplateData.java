package com.hyfly.poitl;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TemplateData {

    private String testText;

    private List<AreaTableData> areaTable;

    private List<TestAreaData> testArea;
}

