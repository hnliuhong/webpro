package cn.canway.dao.impl;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.canway.model.Product;
import cn.canway.util.JdbcUtil;
public class ProductDaoImpl extends BaseDaoImpl<Product> {
	// main方法测试缺陷: 有侵入性. 不方便同时测试. Java建议采用Junit单元测试
	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		return super.update(sql,new Object[]{product.getName(),product.getPrice(),product.getRemark()});
	}

	public int update(Product product) {
		String sql = "update product set name = ?,price = ?,remark = ? where id = ?";
		return super.update(sql,new Object[]{product.getName(),product.getPrice(),product.getRemark(),product.getId()});
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return super.update(sql,new Object[]{id});
	}

	public static void main(String[] args) {
		ProductDaoImpl productDao = new ProductDaoImpl();
		System.out.println(productDao.getById(1));
//		productDao.delete(1);
		for(Product product:productDao.queryByName("")){
			System.out.println(product);
		}
	}

	public Product getById(int id) {
		Product product = null;
		String sql = "select * from product where id = ?";
		product = super.getById(sql,id);
		return product;
	}
	// 模糊查询,可能返回多条件记录(具体数量不确定)
	// 数组  (限大小,限类型)，泛型集合 (不限大小,限制类型)  集合：(不限大小,不限类型) 
	// 在Java中层与层之间的调用,包括返回的类型.尽可能使用接口(面向接口编程)
	public List<Product> queryByName(String keyword) {
		List<Product> proList=new ArrayList<Product>();
		String sql = "select * from product where name like ?";
		return super.queryByName(sql,keyword);
	}

	@Override
	protected Product getRow(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getDouble("price"));
		product.setRemark(rs.getString("remark"));
		product.setDate(rs.getDate("date"));
		return product;
	}
}
