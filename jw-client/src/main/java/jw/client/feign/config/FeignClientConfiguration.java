package jw.client.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {
    @Bean
    public Logger.Level level() { //return Logger.Level.FULL;

        return Logger.Level.FULL;
    }


}
