package com.nacos.jw.customer.jwcustomer.configuration;

import com.nacos.jw.customer.jwcustomer.eneity.Person;
import com.nacos.jw.customer.jwcustomer.importcus.MyImportBeanDefinitionRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyImportBeanDefinitionRegistrar.class})
@ConditionalOnMissingBean({Person.class})
public class SentinelConfiguration {


}
