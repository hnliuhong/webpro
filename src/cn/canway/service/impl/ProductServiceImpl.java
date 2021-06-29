package cn.canway.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.canway.dao.impl.ProductDao;
import cn.canway.model.Product;
import cn.canway.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
// 代理对象和目标对象(被代理对象), 实现相同的接口
public class ProductServiceImpl implements cn.canway.service.ProductService {
	// 直接通过JVM来创建和管理对象有三个缺点: 数量、类型、时间
	private ProductDao productDao = null;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public static void main(String[] args) {
		// 获取spring-bean.xml配置文件,默认加载配置文件时所有的Bean对象都会先自动创建
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-*.xml");
		// 从spring中获取PorducDaoImpl
		ProductService productService = context.getBean("ps", ProductService.class);
//		for (Product product:productService.queryByName("")){
//			System.out.println(product);
//		}
		Product p = new Product(3,"Iphone12",new BigDecimal(8000),"我是备注");
		productService.update(p);
		for(Product product:productService.queryByName("",3,5)){
			System.out.println(product);
		}
//
	}

	@Override
	public int save(Product product) {
		System.out.println("save.................");
		System.out.println(productDao);
//		Integer.parseInt("xxxxxxx");
		return productDao.save(product);
	}

	@Override
	public int update(Product product) {
		return productDao.update(product);
	}

	@Override
	public int delete(int id) {
		return productDao.delete(id);
	}

	@Override
	public Product getById(int id) {
		return productDao.getById(id);
	}

	@Override
	public List<Product> queryByName(String keyword,int page,int size) {
//		select * from product where name like #{keyword} limit #{start},#{size}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("keyword","%" + keyword + "%");
		paramMap.put("start",(page-1) * size);
		paramMap.put("size",size);
		return productDao.queryByName(paramMap);
	}
}
