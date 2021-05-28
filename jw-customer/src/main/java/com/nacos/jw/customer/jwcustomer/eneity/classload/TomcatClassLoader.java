package com.nacos.jw.customer.jwcustomer.eneity.classload;


import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 加载tomcat的lib
 */
public class TomcatClassLoader extends ClassLoader {

    private String path;

    private URLClassLoader urlClassLoader;

    public TomcatClassLoader(ClassLoader parentClassLoad, String pathString) {
        super(parentClassLoad);
        this.path = pathString;
        Path path = Paths.get(this.path);
        File[] files = path.toFile().listFiles();
        List<URL> collect = Arrays.stream(files).filter(file -> file.getName().endsWith(".jar")).map(file -> {
            try {
                return file.toURI().toURL();
            } catch (MalformedURLException e) {
                return null;
            }
        }).collect(Collectors.toList());
        this.urlClassLoader = new URLClassLoader(collect.toArray(new URL[]{}));
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return urlClassLoader.loadClass(name);
    }
}
