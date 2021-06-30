package cn.canway.util;

import cn.canway.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitDataListener extends ContextLoader implements ServletContextListener {

    @Override  // 项目启动时运行,且优先级最高
    public void contextInitialized(ServletContextEvent se) {
        System.out.println("contextInitialized............");
        // 在application内置对象去获取已经加载的Spring配置文件
        ApplicationContext context = (ApplicationContext) se.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
        ProductService productService = context.getBean("productService", ProductService.class);
        System.out.println(productService);
    }

    @Override  // 项目关闭时运行
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed............");
    }
}
