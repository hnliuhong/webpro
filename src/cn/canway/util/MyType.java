package cn.canway.util;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.SQLOutput;
import java.text.Format;

// *.java 源码文件, *.class 文件
public class MyType {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Class getClassFile() throws ClassNotFoundException {
        // Java纯面向对象, 一切皆为对象
        // 获取*.class文件一共有三种方式
        // Class官方定义类型,与File相同. clazz是当前类型的对象(实例)
        Class clazz = MyType.class; // 获取当前MyType.class
        System.out.println(clazz);
        Class clazz2 = Class.forName("cn.canway.util.MyType");
        // 根据对象反向获取对象的类型
        MyType myType = new MyType();
        Class clazz3 = myType.getClass();
        System.out.println(clazz == clazz2);
        System.out.println(clazz3 == clazz2);
        return clazz;
    }

    // 反射是运行时,因此它获取的是class文件
    public static void main(String[] args) throws Exception {
        MyType myType = new MyType();
        myType.setName("admin");
        System.out.println(myType.getName());
        // Class文件有属性和方法(反射实现上面的功能)
        Class clazz = MyType.class;
        for (Field field:clazz.getDeclaredFields()){
            System.out.println(field);
        }
        // 根据名称获取指定的私有字段
        Field field = clazz.getDeclaredField("name");
        // 对象.属性 = 值    class: 属性.set(对象,值)
        field.set(myType,"张三");
        System.out.println(myType.getName());
    }
}
