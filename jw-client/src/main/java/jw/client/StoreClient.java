package jw.client;

import jw.client.dto.Store;
import jw.client.feign.config.FeignClientConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "user-service",configuration = FeignClientConfiguration.class)
public interface StoreClient {

    @RequestMapping(method = RequestMethod.GET, value = "/feign")
    Store getStores();

    @RequestMapping(value = "/getServiceList")
    List<ServiceInstance> getServiceList(@RequestParam("serviceName") String serviceName) ;
}


