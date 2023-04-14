package com.clamos.io.collect.controller;

import com.clamos.io.collect.component.ConnectorListener;
import com.clamos.io.collect.service.SparkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spark")
@RequiredArgsConstructor
public class SparkController {
    private final SparkService sparkService;
    private final ConnectorListener connectorListener;

    @PostMapping("/test")
    public void test() throws Exception {
        sparkService.sparkConnection();
    }

    @PostMapping("/connect")
    public void connect() throws Exception {
    }
}
