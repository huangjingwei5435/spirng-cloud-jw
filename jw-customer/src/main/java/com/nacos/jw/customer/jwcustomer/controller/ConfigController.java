package com.nacos.jw.customer.jwcustomer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache}")
    private String useLocalCache;
    @Value("${config1}")
    private String config1;

    @RequestMapping("/get")
    // @SentinelResource(value = "sentinelGet", blockHandler = "sentinelGetBlockHandler")
    public String get() {
        return config1;
    }

    /**
     * 被限流后处理的方法，要求:出参跟原来方法一致，入参数跟原来方法要一致，还可以加一个BlockException类型的参数
     *
     * @param e
     * @return
     */
//    public String sentinelGetBlockHandler(BlockException e){
//        System.out.println("您已经被流控了");
//        return "您已经被流控了";
//    }
}
