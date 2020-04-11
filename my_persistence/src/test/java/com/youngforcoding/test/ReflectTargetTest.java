package com.youngforcoding.test;

import com.youngforcoding.test.annotation.Custom;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-05 7:13 PM   
 *  *    
 *  
 */
public class ReflectTargetTest<T> extends ReflectTargetFuTest{

    private @Deprecated T target;

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }


    public List<T> getTargetList() {
        //泛型类型不能被实例化
        //T t = new T();
        List<T> list = new ArrayList<>();
        list.add(target);
        return list;
    }

    public List<String> getTargetList02(){
        return null;
    }

    public <@Custom E> void test(E e){

    }

    public <E> E test02(){
        return null;
    }

    private void sayHello(){
        System.out.println("hello");
    }

    class InnerClass{
        private int i;
    }

}
