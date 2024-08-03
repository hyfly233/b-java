package com.hyfly.doristest.controller;

import com.hyfly.doristest.entity.ExampleTbl;
import com.hyfly.doristest.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final ITestService testService;

    public TestController(@Autowired ITestService testService) {
        this.testService = testService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PutMapping("/load")
    public String loadData(@RequestBody List<ExampleTbl> data) {
        return testService.loadData(data);
    }
}
