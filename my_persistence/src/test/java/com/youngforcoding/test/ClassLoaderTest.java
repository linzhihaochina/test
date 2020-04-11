package com.youngforcoding.test;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *    
 *  *  
 *  * @Description:  获取类加载器的三种方式   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-03 7:10 AM   
 *  *    
 *  
 */
public class ClassLoaderTest {


    /**
     * jdk11和jdk8不一样
     * jdk11把类加载器的位置移动了
     * <p>
     * jdk11
     * jdk.internal.loader.ClassLoaders$AppClassLoader@277050dc
     * jdk.internal.loader.ClassLoaders$PlatformClassLoader@18e8568
     * null
     * <p>
     * jdk8
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * sun.misc.Launcher$ExtClassLoader@5d6f64b1
     * null
     */
    @Test
    public void testClassLoader1() {
        //1、通过class对象获取
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();

        //2、通过当前线程获取
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        //3、通过ClassLoader的静态方法获取
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        System.out.println(contextClassLoader);
        System.out.println(systemClassLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }


    /**
     * @throws MalformedURLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    @Test
    public void testClassLoader2() throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        //获取classloader的classpath
        //URLClassLoader classLoader = (URLClassLoader) ClassLoaderTest.class.getClassLoader();
        //ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //URL url = classLoader.getResource("./");
        //  创建一个ClassLoader对象，指定一个classpath，和父类加载器为null
        URLClassLoader classLoader2 = new URLClassLoader(new URL[]{new URL("file:/Users/linzhihao/test/")}, null);
        System.out.println("父亲是" + classLoader2.getParent());
        //  加载一个本地的class文件到内存中
        Class test = classLoader2.loadClass("com.youngforcoding.test.Test");
        //JDBCTest jdbcTest = (JDBCTest) jdbcTestClass.newInstance();
        //jdbcTest.main(new String[]{"1", "2", "3"});
        Object testObj = test.newInstance();
        Method method = test.getMethod("everything", String.class);
        System.out.println(method.getName());
        System.out.println(method.getParameterCount());
        for (Class<?> aClass : method.getParameterTypes()) {
            System.out.println(aClass.getName());
        }
        method.invoke(testObj, "aa");
        //System.out.println(jdbcTestClass);

    }
}
