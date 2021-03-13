package com.jw.cloud.jwprovider;

import jw.client.StoreClient;
import jw.client.dto.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "jw.client")
public class JwProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwProviderApplication.class, args);
    }

    @RestController
    static class EchoController implements StoreClient {

        @Autowired
        private DiscoveryClient discoveryClient;

        @Override
        public Store getStores() {
            System.out.println("收到feign调用");
            return Store.of("咖啡店", "西西天街999");
        }

        @Override
        public List<ServiceInstance> getServiceList(String serviceName) {
            return discoveryClient.getInstances(serviceName);
        }


    }
}
