package cn.canway.dao.impl;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.canway.model.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ProductDaoImpl{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// main方法测试缺陷: 有侵入性. 不方便同时测试. Java建议采用Junit单元测试
	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		int num = jdbcTemplate.update(sql,new Object[]{product.getName(),product.getPrice(),product.getRemark()});
		return num;
	}

	public int update(Product product) {
		String sql = "update product set name = ?,price = ?,remark = ? where id = ?";
		return jdbcTemplate.update(sql,new Object[]{product.getName(),product.getPrice(),product.getRemark(),product.getId()});
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return jdbcTemplate.update(sql,new Object[]{id});
	}

	public Product getById(int id) {
		String sql = "select * from product where id=?";
		return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Product>(Product.class),id);
	}
	// 模糊查询,可能返回多条件记录(具体数量不确定)
	// 数组  (限大小,限类型)，泛型集合 (不限大小,限制类型)  集合：(不限大小,不限类型) 
	// 在Java中层与层之间的调用,包括返回的类型.尽可能使用接口(面向接口编程)
	public List<Product> queryByName(String keyword) {
		String sql = "select * from product where name like ?";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Product>(Product.class),"%" + keyword+"%");
	}
}
