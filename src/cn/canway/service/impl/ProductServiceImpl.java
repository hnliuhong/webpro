package cn.canway.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import cn.canway.dao.impl.ProductDaoImpl;
import cn.canway.model.Product;
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
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
		// 从spring中获取PorducDaoImpl
		ProductServiceImpl serviceImpl = context.getBean("ps",ProductServiceImpl.class);
		Product p = new Product(null,"AOP",new BigDecimal(300),"我是备注");
		serviceImpl.save(p);
		for (Product product:serviceImpl.queryByName("")){
			System.out.println(product);
		}
	}

	@Override
	public int save(Product product) {
//		Connection con = 对象;
		Date date = new Date();
		// 手动开启事务......
//		return productDaoImpl.save(product);
		System.out.println("完成核心业务逻辑.......");
		// 手动提交事务......
		return 0;
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
		System.out.println("查询核心业务");
		return null;
	}

	@Override
	public List<Product> queryByName(String keyword) {
		return productDaoImpl.queryByName(keyword);
	}
}
