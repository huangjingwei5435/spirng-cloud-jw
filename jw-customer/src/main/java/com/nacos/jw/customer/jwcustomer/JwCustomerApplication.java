package com.nacos.jw.customer.jwcustomer;

import jw.client.StoreClient;
import jw.client.dto.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "jw.client")
@Slf4j
public class JwCustomerApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(JwCustomerApplication.class, args);

    }

    @Slf4j
    @Component
    static class DataLoader implements CommandLineRunner {
        @Override
        public void run(String... strings) throws Exception {
            log.info("Loading data...");
        }
    }

    @RestController
    public static class TestController {

        @Autowired
        private final RestTemplate restTemplate;
        @Autowired
        private StoreClient storeClient;

        public TestController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
        public String echo(@PathVariable String str) {
            return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
        }

        @RequestMapping(value = "feignTest", method = RequestMethod.GET)
        public Store feignTest() {
            System.out.println("收到调用feign接口");
            return storeClient.getStores();
        }

        @RequestMapping(value = "getServiceList", method = RequestMethod.GET)
        public List<ServiceInstance> serverListGet(@RequestParam("serviceName") String serviceName) {
            System.out.println("开始获取服务列表");
            return storeClient.getServiceList(serviceName);
        }
    }
}
