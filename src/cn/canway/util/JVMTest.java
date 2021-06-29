package cn.canway.util;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// 引用类型: 强引用、软引用、弱引用、虚引用
class MyObject extends Object{
    // 重写垃圾回收  -Xms 初始堆大小  -Xmx 最大堆  (对象就存在堆中)
    @Override  // 子类重些finalize方法
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyObject finalize.......");
    }
}

public class JVMTest {

    public static void main(String[] args) throws Exception {
        // 强引用：即使堆内存溢出对象都不会被释放
        MyObject ref1 = new MyObject();
        // 软引用：如果堆内存不够,则支持垃圾回收 (缓存)
      SoftReference<MyObject> ref2 = new SoftReference<MyObject>(new MyObject());
        // 弱引用：只要垃圾回收就会被释放 (缓存+高效同步)
        WeakReference<MyObject> ref3 = new WeakReference<MyObject>(new MyObject());
        List<Object> oList = new ArrayList<Object>();
        for(int i=1;i<=10;i++){
            System.out.println("i=" + i);
            oList.add(new byte[1024 * 1024]);
            System.gc(); // 手动调用垃圾回收!
            Thread.sleep(1000);
        }
        // 类加载器: 类加载到JVM用的就是类加载器
        ClassLoader loader = JVMTest.class.getClassLoader();
        System.out.println(loader);
    }
}
