package cn.canway.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import cn.canway.dao.impl.ProductDaoImpl;
import cn.canway.model.Product;
import cn.canway.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
// 代理对象和目标对象(被代理对象), 实现相同的接口
public class ProductServiceImpl implements cn.canway.service.ProductService {
	// 直接通过JVM来创建和管理对象有三个缺点: 数量、类型、时间
	private ProductDaoImpl productDaoImpl = null;

	public void setProductDaoImpl(ProductDaoImpl productDaoImpl) {
		this.productDaoImpl = productDaoImpl;
	}

	public static void main(String[] args) {
		// 获取spring-bean.xml配置文件,默认加载配置文件时所有的Bean对象都会先自动创建
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-*.xml");
		// 从spring中获取PorducDaoImpl
		ProductService productService = context.getBean("ps", ProductService.class);
		for (Product product:productService.queryByName("")){
			System.out.println(product);
		}
		Product p = new Product(null,"AOP",new BigDecimal(300),"我是备注");
		productService.save(p);
//
	}

	@Override
	public int save(Product product) {
		System.out.println("save.................");
//		Integer.parseInt("xxxxxxx");
		return productDaoImpl.save(product);
	}

	@Override
	public int update(Product product) {
		return productDaoImpl.update(product);
	}

	@Override
	public int delete(int id) {
		return productDaoImpl.delete(id);
	}

	@Override
	public Product getById(int id) {
		return productDaoImpl.getById(id);
	}

	@Override
	public List<Product> queryByName(String keyword) {
		return productDaoImpl.queryByName(keyword);
	}
}
