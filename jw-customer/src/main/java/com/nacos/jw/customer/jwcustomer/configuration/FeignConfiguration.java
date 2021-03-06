package com.nacos.jw.customer.jwcustomer.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.nacos.jw.customer.jwcustomer.eneity.Person;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FeignConfiguration {
//    @Bean
//    public Encoder feignEncoder() {
//        return new SpringEncoder(feignHttpMessageConverter());
//    }
//
//    private ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
//        final HttpMessageConverters httpMessageConverters =
//                new HttpMessageConverters(getFastJsonConverter());
//        return () -> httpMessageConverters;
//    }
//
//    private FastJsonHttpMessageConverter getFastJsonConverter() {
//        FastJsonHttpMessageConverter converter =
//                new FastJsonHttpMessageConverter();
//
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
//        MediaType mediaTypeJson =
//                MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE);
//        supportedMediaTypes.add(mediaTypeJson);
//        converter.setSupportedMediaTypes(supportedMediaTypes);
//        FastJsonConfig config = new FastJsonConfig();
//        config.getSerializeConfig()
//                .put(Json.class, new SwaggerJsonSerializer());
//        config.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect);
//        converter.setFastJsonConfig(config);
//
//        return converter;
//    }

    @Bean
    public Person person() {
        return new Person();
    }
}
