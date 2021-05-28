package com.nacos.jw.customer.jwcustomer.scanner;

import com.nacos.jw.customer.jwcustomer.annotation.MyMapper;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/***
 * 自定义扫描器
 * @author huangjingwei
 */
public class MyClasssPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public MyClasssPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected void registerDefaultFilters() {
        addIncludeFilter(new AnnotationTypeFilter(MyMapper.class));
    }
}
