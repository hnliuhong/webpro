package cn.canway.util;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

// 面向切面编程的类 Aspect
public class AopClass {

    public void before(JoinPoint joinPoint){
        System.out.println("before................");
        System.out.println("被切入被代理对象:" + joinPoint.getTarget());
        System.out.println("被切入的方法" + joinPoint.getSignature());
        System.out.println("被切入方法的参数:" + Arrays.toString(joinPoint.getArgs()));
    }
    // 某个方法运行之后切入(前置通知)
    // AOP中，一个连接点总是表示一个方法的执行
    public void after_return(JoinPoint joinPoint){
        System.out.println("被切入被代理对象:" + joinPoint.getTarget());
        System.out.println("被切入的方法" + joinPoint.getSignature());
        System.out.println("被切入方法的参数:" + Arrays.toString(joinPoint.getArgs()));
    }

    public void after_execption(JoinPoint joinPoint,Exception e){
        System.out.println("被切入被代理对象:" + joinPoint.getTarget());
        System.out.println("被切入的方法" + joinPoint.getSignature());
        System.out.println("被切入方法的参数:" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("异常信息为:" + e.getMessage());
    }
}
