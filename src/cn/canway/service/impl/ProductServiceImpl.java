package cn.canway.service.impl;

import java.util.List;

import cn.canway.dao.impl.ProductDaoImpl;
import cn.canway.model.Product;

public class ProductServiceImpl {

	private ProductDaoImpl productDaoImpl = new ProductDaoImpl();

	public int save(Product product) {
		return productDaoImpl.save(product);
	}

	public int update(Product product) {
		return productDaoImpl.update(product);
	}

	public int delete(int id) {
		return productDaoImpl.delete(id);
	}

	public Product getById(int id) {
		return productDaoImpl.getById(id);
	}

	public List<Product> queryByName(String keyword) {
		return productDaoImpl.queryByName(keyword);
	}
}
