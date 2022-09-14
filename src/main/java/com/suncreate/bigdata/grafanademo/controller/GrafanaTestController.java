package com.suncreate.bigdata.grafanademo.controller;

import com.suncreate.bigdata.grafanademo.util.RestResponse;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/gateway/metrics")
public class GrafanaTestController {

    @Autowired
    private MeterRegistry meterRegistry;

    private Counter counter;

    @PostConstruct
    public void init() {
        Tags tags = Tags.of("common", "test");
        // 公共标签
        meterRegistry.config().commonTags(tags);
        counter = Counter.builder("metrics.request.common").register(meterRegistry);
    }

    /**
     * 订单请求测试
     */
    @GetMapping("/order/{appId}")
    public RestResponse<String> orderTest(@PathVariable("appId") String appId) {
        counter.increment();
        Counter.builder("metrics.request.count").tags("appMark", appId, "apiCode", "order").register(meterRegistry).increment();
        return RestResponse.ok(appId);
    }

    /**
     * 产品请求测试
     */
    @GetMapping("/product/{appId}")
    public RestResponse<String> productTest(@PathVariable("appId") String appId) {
        counter.increment();
        Counter.builder("metrics.request.count").tags("appMark", appId, "apiCode", "product").register(meterRegistry).increment();
        return RestResponse.ok(appId);
    }
}
