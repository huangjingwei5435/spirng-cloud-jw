package com.nacos.jw.customer.jwcustomer.eneity;

import com.nacos.jw.customer.jwcustomer.eneity.classload.TomcatClassLoader;
import com.nacos.jw.customer.jwcustomer.eneity.classload.WebAppClassLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TomcatTest {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        ClassLoader.getSystemClassLoader();

        //通用加载器
        TomcatClassLoader commonLoader = new TomcatClassLoader(Thread.currentThread().getContextClassLoader(),"/Users/huangjingwei/Desktop/tomcat/all");
        Class<?> aClass1 = commonLoader.loadClass("org.apache.logging.log4j.Level");
        System.out.println(aClass1);

        //tomcat私有加载器
        TomcatClassLoader catalinaLoader = new TomcatClassLoader(commonLoader, "/Users/huangjingwei/Desktop/tomcat/prilib");
        //Class<?> aClass2 = catalinaLoader.loadClass("com.mysql.cj.jdbc.Driver");
        Class<?> catalinaLoaderLevel = catalinaLoader.loadClass("org.apache.logging.log4j.Level");
        System.out.println(catalinaLoaderLevel);

        //tomcat公用加载器
        TomcatClassLoader sharedLoader = new TomcatClassLoader(commonLoader, "/Users/huangjingwei/Desktop/tomcat/sharlib");
        //Class<?> aClass3 = sharedLoader.loadClass("com.mysql.jdbc.Driver");
        Class<?> sharedLoaderLevel = catalinaLoader.loadClass("org.apache.logging.log4j.Level");
        System.out.println(sharedLoaderLevel);

        Path path = Paths.get("/Users/huangjingwei/Desktop/tomcat/webapp");
        File[] files = path.toFile().listFiles();
        for (File file : files) {
            if (!file.isDirectory()){
                continue;
            }
            WebAppClassLoader webAppClassLoader = new WebAppClassLoader(sharedLoader, file.getName());
            Class<?> aClass = webAppClassLoader.loadClass("com.nacos.jw.customer.jwcustomer.eneity.PersonOne");
            Method printMethod = aClass.getDeclaredMethod("print");
            printMethod.invoke(aClass.newInstance());
        }

    }
}
