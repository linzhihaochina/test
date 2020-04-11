package com.youngforcoding.test;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  类反射测试   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-03 6:57 PM   
 *  *    
 *  
 */
public class ReflectTest {

    @Test
    public void reflectTest01() throws NoSuchMethodException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        Class<ReflectTest> testClass = ReflectTest.class;
        Method method = testClass.getMethod("testList");
        Type type = method.getGenericReturnType();
        if (type instanceof ParameterizedType) {
            //System.out.println(((ParameterizedType) type).getRawType());
            //System.out.println(((ParameterizedType) type).getOwnerType());
            for (Type type1 : ((ParameterizedType) type).getActualTypeArguments()) {
                Class clazz = Class.forName(type1.getTypeName());

                System.out.println(type1);
            }

        }
        System.out.println(type);
    }

    //command + 7显示类的结构
    @Test
    public void reflectTest02() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        //Cannot select from parameterized type
        //Class<ReflectTargetTest<String>> targetTest = ReflectTargetTest<String>.class;
        Class<ReflectTargetTest> targetTest = ReflectTargetTest.class;
        //1、获取指定元数据对象的所有Field(public修饰的)，包括父类的属性
//        Field[] publicFields = targetTest.getFields();
//        for (Field publicField : publicFields) {
//            System.out.println(publicField);
//        }
        //类型参数化
        //ReflectTargetTest targetTest1 = targetTest.newInstance();

        targetTest = (Class<ReflectTargetTest>) new ReflectTargetTest<String>().getClass();
        Field[] allFields = targetTest.getDeclaredFields();
        //ReflectTargetTest testObj = new ReflectTargetTest();
        ReflectTargetTest<String> testObj = new ReflectTargetTest<>();
        testObj.setTarget("111");
        for (Field field : allFields) {
            //  允许访问私有成员变量
            field.setAccessible(true);

            //System.out.println(field);
            //  属性名
            System.out.println("属性的名称:" + field.getName());
            //  获取testObj的field的值
            System.out.println("testObj对象的" + field.getName() + "属性值为:" + field.get(testObj));
            //  获取属性的类型,如果是不确定的泛型那么类型将会是Object
            System.out.println("属性的类型为:" + field.getType());
            //  获取访问修饰符
            System.out.println("属性的访问权限是:" + Modifier.toString(field.getModifiers()));

            System.out.println(field.getAnnotatedType());

            //  获取属性上的所有注解对象
            System.out.println(field.getAnnotations()[0]);

            //  获取属性上指定的注解对象
            System.out.println(field.getAnnotation(Override.class));

            System.out.println(field.getAnnotationsByType(Override.class));
            System.out.println(field.getAnnotationsByType(Deprecated.class));
        }


        Class<com.youngforcoding.test.Test> clazz = com.youngforcoding.test.Test.class;
        Field field = clazz.getDeclaredField("innerClass");
        ParameterizedType type = (ParameterizedType) field.getGenericType();
        System.out.println("???" + type.getOwnerType());

        Field aa = clazz.getDeclaredField("aa");
        Type type2 = aa.getGenericType();
        System.out.println(type2 instanceof Class);


        Class cl = int.class;

    }

    @Test
    public void test01() throws NoSuchMethodException {
        Class<ReflectTargetTest> reflectTargetTestClass = ReflectTargetTest.class;
        System.out.println(reflectTargetTestClass.getDeclaredMethod("getTarget").getGenericReturnType().getClass());
        System.out.println(reflectTargetTestClass.getDeclaredMethod("getTargetList").getGenericReturnType().getClass());
        System.out.println(reflectTargetTestClass.getDeclaredMethod("getTargetList02").getGenericReturnType().getClass());
        System.out.println(reflectTargetTestClass.getDeclaredMethod("test", Object.class).getGenericReturnType().getClass());
        System.out.println(reflectTargetTestClass.getDeclaredMethod("test02").getGenericReturnType().getClass());

    }

    @Test
    public void test02() throws Exception{
        Class<ReflectTargetTest> reflectTargetTestClass = ReflectTargetTest.class;
        System.out.println(reflectTargetTestClass.getDeclaredMethod("setTarget",Object.class).getReturnType());
    }


    public List<String> testList() {
        return new ArrayList<>();
    }


    @Test
    public void testArrayCopy() {
        int[] arr = new int[]{1, 3, 4, 5, 6};
        int[] arr2 = new int[3];
        System.arraycopy(arr, 0, arr2, 1, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));
    }

}
