package cn.canway.controller;

import cn.canway.service.ProductService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller // 此Controller主要用来声明公共Service与JSP内置对象
public class BaseController {

    @Resource(name = "ps")
    protected ProductService productService;
    @Resource  // Request  Session Appliation 都支持安装类型自动注入
    protected HttpServletRequest request;
    @Resource
    protected HttpSession session;
    @Resource
    protected ServletContext application;
}
