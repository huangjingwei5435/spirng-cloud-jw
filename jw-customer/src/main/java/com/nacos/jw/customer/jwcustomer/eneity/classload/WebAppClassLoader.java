package com.nacos.jw.customer.jwcustomer.eneity.classload;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class WebAppClassLoader extends ClassLoader {

    public static final String FIX_PATH = "/Users/huangjingwei/Desktop/tomcat/webapp/";

    private String appName;

    private URLClassLoader urlClassLoader;

    public WebAppClassLoader(ClassLoader parent, String appName) throws MalformedURLException {
        super(parent);
        this.appName = appName;
        String path = FIX_PATH + appName + "/" + "jw-sdk-0.0.1-SNAPSHOT.jar";
        urlClassLoader = new URLClassLoader(new URL[]{Paths.get(path).toFile().toURI().toURL()});
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return urlClassLoader.loadClass(name);
    }
}
