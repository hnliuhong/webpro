package cn.canway.controller;
// 控制层,主要接受前端请求,并且调用后端Service

import cn.canway.model.Product;
import cn.canway.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// mvc支持注解,前提是必须在配置文件中开启注解扫描
@Controller  // 告知此是Controller(它可以通过Http请求访问)
@RequestMapping("/product")   // 告知访问此类的地址:
public class ProductController extends BaseController {  // 单例模式

    // form表单中的name名称与product属性相同,则自动赋值
    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        // redirect forward 默认都自带工程名
        return "redirect:/show.jsp";
    }

    @RequestMapping("/queryByName")
    public String queryByName(String keyword){
        System.out.println(request);  // 每次请求都是request
        System.out.println(session);  // 每个人当前有一个session
        System.out.println(application);  // 整个系统只有一个application
        List<Product> proList =  productService.queryByName(keyword,1,5);
        request.setAttribute("proList",proList);
        session.setAttribute("proList",proList);
        application.setAttribute("proList",proList);
        // redirect forward 默认都自带工程名
        return "forward:/show.jsp";
    }
}
