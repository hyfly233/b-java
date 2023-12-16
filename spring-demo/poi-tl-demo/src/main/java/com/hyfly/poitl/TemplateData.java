package com.hyfly.poitl;

import com.deepoove.poi.expression.Name;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TemplateData {

    private String testText;

    @Name("area_table")
    private List<AreaTableData> areaTable;

    private List<TestAreaData> testArea;
}

