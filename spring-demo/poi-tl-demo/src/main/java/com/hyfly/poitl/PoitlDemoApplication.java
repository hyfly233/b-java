package com.hyfly.poitl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class PoitlDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(PoitlDemoApplication.class, args);

        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        // 获取 resource 目录下的 template.docx 文件路径
        String resourcePath = Objects.requireNonNull(PoitlDemoApplication.class.getClassLoader().getResource("template.docx")).getPath();

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();

        Configure config = Configure.builder()
                .bind("area_table", policy)
                .build();

        XWPFTemplate template = XWPFTemplate.compile(resourcePath, config).render(getTemplateData());
        template.writeAndClose(new FileOutputStream("outputs/output.docx"));
    }

    public static List<AreaTableData> getAreaTableDataList() {
        return new ArrayList<>() {{
            add(new AreaTableData().setNo(1).setName("区域1").setDesc("描述1").setNum(100));
            add(new AreaTableData().setNo(2).setName("区域2").setDesc("描述2").setNum(200));
            add(new AreaTableData().setNo(3).setName("区域3").setDesc("描述3").setNum(300));
            add(new AreaTableData().setNo(4).setName("区域4").setDesc("描述4").setNum(400));
            add(new AreaTableData().setNo(5).setName("区域5").setDesc("描述5").setNum(500));
        }};
    }

    public static TemplateData getTemplateData() {
        TemplateData data = new TemplateData();
        data.setTestText("Hi, poi-tl Word模板引擎");

        data.setAreaTable(getAreaTableDataList());

        List<TestAreaData> testAreaDataList = new ArrayList<>();
        testAreaDataList.add(new TestAreaData().setAreaTableName("TestArea标题1").setAreaTable(getAreaTableDataList()));
        testAreaDataList.add(new TestAreaData().setAreaTableName("TestArea标题2").setAreaTable(getAreaTableDataList()));
        testAreaDataList.add(new TestAreaData().setAreaTableName("TestArea标题3").setAreaTable(getAreaTableDataList()));

        data.setTestArea(testAreaDataList);

        return data;
    }
}
