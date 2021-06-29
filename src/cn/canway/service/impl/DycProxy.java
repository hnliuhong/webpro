package cn.canway.service.impl;

import cn.canway.model.Product;
import cn.canway.service.ProductService;

import javax.sound.midi.Soundbank;
import javax.xml.transform.sax.SAXSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Arrays;

class MyInvocationHandler implements InvocationHandler {

    private Object target = null;

    public MyInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        System.out.println("动态生成代理类:" + proxy.getClass());
        System.out.println("被调用的目标方法" + method.getName());
        System.out.println("被调用的目标方法的参数" + Arrays.toString(objects));
        Object res;
        if (method.getName().equals("save")){
            System.out.println("事务开启......");
            res = method.invoke(target,objects);
            System.out.println("事务结束......");
        }else{
            res = method.invoke(target,objects);
        }
        return res;
    }
}

// 根据反射,创建动态代理类
public class DycProxy {

    public static void main(String[] args) throws Exception {
        // 无论是静态还是动态都需要目标对象,并且和目标对象实现相同接口
        ProductServiceImpl target = new ProductServiceImpl();
        ClassLoader loader = target.getClass().getClassLoader();
        Class<?>[] inters = target.getClass().getInterfaces();
        ProductService proxy = (ProductService)Proxy.newProxyInstance(loader,inters,new MyInvocationHandler(target));
        proxy.save(new Product(null,"AAA",new BigDecimal(3.14),"我是备注"));
        proxy.getById(100);
    }
}
