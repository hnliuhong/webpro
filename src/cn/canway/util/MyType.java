package cn.canway.util;

public class MyType {

    static {
        System.out.println("在被加载JVM中static才会被执行, 且执行一次,一般用来加载资源");
    }

    public static void main(String[] args) {
        System.out.println("=======");
    }
}
