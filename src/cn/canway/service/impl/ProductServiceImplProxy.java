package cn.canway.service.impl;

import cn.canway.dao.impl.ProductDaoImpl;
import cn.canway.model.Product;
import cn.canway.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

// 代理对象,需要完成: ProductServiceImpl 非核心操作
public class ProductServiceImplProxy implements cn.canway.service.ProductService {
	// 被代理类与代理类一样实现相同的接口
	private ProductService productService = null;

	public ProductServiceImplProxy(ProductService productService){
		this.productService = productService;
	}

	public static void main(String[] args) {
		// 代理对象执行之前,必须要传入一个目标对象
		ProductServiceImpl target = new ProductServiceImpl();
		ProductServiceImplProxy proxy = new ProductServiceImplProxy(target);
		proxy.save(new Product(null,"AAA",new BigDecimal(300),"我是备注哈!"));
		// 动态代理的采用的就是反射机制
		Class clazz =ProductServiceImpl.class.getInterfaces()[0];
		for (Method method:clazz.getDeclaredMethods()){
			System.out.println(method);
		}
	}

	@Override
	public int save(Product product) {
		System.out.println("开始事务..............");
		int count = productService.save(product);
		System.out.println("提交事务..............");
		return count;
	}

	@Override
	public int update(Product product) {
		System.out.println("开始事务..............");
		int count = productService.update(product);
		System.out.println("提交事务..............");
		return count;
	}

	@Override
	public int delete(int id) {
		System.out.println("开始事务..............");
		int count = productService.delete(id);
		System.out.println("提交事务..............");
		return count;
	}

	@Override
	public Product getById(int id) {
		return productService.getById(id);
	}

	@Override
	public List<Product> queryByName(String keyword) {
		return productService.queryByName(keyword);
	}
}
