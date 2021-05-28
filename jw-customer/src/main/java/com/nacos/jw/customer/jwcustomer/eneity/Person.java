package com.nacos.jw.customer.jwcustomer.eneity;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class Person {

    private static final ThreadLocalRandom RANDOM_LOCAL = ThreadLocalRandom.current();

    public void print() {
        System.out.println("666");
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        Path path = Paths.get("/Users/huangjingwei/Desktop/test/target/jw-sdk-0.0.1-SNAPSHOT.jar");
//        Class<?> aClass = new MyClassLoad("/Users/huangjingwei/Desktop").
//                loadClass("com.nacos.jw.customer.jwcustomer.eneity.PersonOne");

//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{path.toUri().toURL()}, Thread.currentThread().getContextClassLoader());
//        Class<?> aClass = urlClassLoader.loadClass("com.nacos.jw.customer.jwcustomer.eneity.PersonOne");
//        System.out.println(aClass.getAnnotations()[0].annotationType());

        MyClassLoad myClassLoad = new MyClassLoad("/Users/huangjingwei/Desktop/test/target/classes/");
        Class<?> aClass = myClassLoad.loadClass("com.nacos.jw.customer.jwcustomer.eneity.PersonOne");
        System.out.println(aClass.newInstance());

    }

    public static class MyClassLoad extends ClassLoader {

        private final String classPath;

        public MyClassLoad(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

//        @Override
//        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//            Class<?> c = findLoadedClass(name);
//            synchronized (getClassLoadingLock(name)) {  // First, check if the class has already been loaded  Class<?> c = findLoadedClass(name);   if(c==null){
//
//                // If still not found, then invoke findClass in order
//                // to find the class.
//                long t1 = System.nanoTime();
//                //c = findClass(name);
//
//                //非自定义的类还是走双亲委派加载
//                if (!name.startsWith("com.nacos.jw")) {
//                    c = this.getParent().loadClass(name);
//                } else {
//                    c = findClass(name);
//                }
//                // this is the defining class loader; record the stats
//                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                sun.misc.PerfCounter.getFindClasses().increment();
//            }
//            if (resolve) {
//                resolveClass(c);
//            }
//            return c;
//        }
    }
}