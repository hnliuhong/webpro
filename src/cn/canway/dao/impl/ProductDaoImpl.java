package cn.canway.dao.impl;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.canway.model.Product;
import cn.canway.util.JdbcUtil;

public class ProductDaoImpl {
	// main方法测试缺陷: 有侵入性. 不方便同时测试. Java建议采用Junit单元测试
//	 public static void main(String[] args) {}

	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		// 1: 获取connection对象
		Connection conn = null;
		// 2: 预编译SQL语句 (解决SQL注入)
		PreparedStatement state = null;
		try {
			conn = JdbcUtil.getConnection();
			state = conn.prepareStatement(sql);
			// 3: 设置参数,执行SQL
			state.setString(1, product.getName());
			state.setDouble(2, product.getPrice());
			state.setString(3, product.getRemark());
			// 4: 执行SQL并且返回受影响行数,先执行finnally在返回
			return state.executeUpdate();
			// 5: 释放资源
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("----finally----");
			JdbcUtil.close(null, state, conn);
		}
	}

	public int update(Product product) {
		String sql = "update product set name = ?,price = ?,remark = ? where id = ?";
		// 1: 获取connection对象
		Connection conn = null;
		// 2: 预编译SQL语句 (解决SQL注入)
		PreparedStatement state = null;
		try {
			conn = JdbcUtil.getConnection();
			state = conn.prepareStatement(sql);
			// 3: 设置参数,执行SQL
			state.setString(1, product.getName());
			state.setDouble(2, product.getPrice());
			state.setString(3, product.getRemark());
			state.setInt(4, product.getId());
			// 4: 执行SQL并且返回受影响行数,先执行finnally在返回
			return state.executeUpdate();
			// 5: 释放资源
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("----finally----");
			JdbcUtil.close(null, state, conn);
		}

	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		// 1: 获取connection对象
		Connection conn = null;
		// 2: 预编译SQL语句 (解决SQL注入)
		PreparedStatement state = null;
		try {
			conn = JdbcUtil.getConnection();
			state = conn.prepareStatement(sql);
			// 3: 设置参数,执行SQL
			state.setInt(1, id);
			// 4: 执行SQL并且返回受影响行数,先执行finnally在返回
			return state.executeUpdate();
			// 5: 释放资源
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("----finally----");
			JdbcUtil.close(null, state, conn);
		}
	}

	public Product getById(int id) {
		Product product = null;
		String sql = "select * from product where id = ?";
		// 1: 获取connection对象
		Connection conn = null;
		// 2: 预编译SQL语句 (解决SQL注入)
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			state = conn.prepareStatement(sql);
			// 3: 设置参数,执行SQL
			state.setInt(1, id);
			// 4: 执行SQL并且返回受影响行数,先执行finnally在返回
			rs = state.executeQuery();
			// 5: 从结果集中获取记录,然后转化为Java Model
			if (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setRemark(rs.getString("remark"));
				product.setDate(rs.getDate("date"));
			}
			return product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("----finally----");
			// 5: 释放资源
			JdbcUtil.close(rs, state, conn);
		}
	}
	// 模糊查询,可能返回多条件记录(具体数量不确定)
	// 数组  (限大小,限类型)，泛型集合 (不限大小,限制类型)  集合：(不限大小,不限类型) 
	// 在Java中层与层之间的调用,包括返回的类型.尽可能使用接口(面向接口编程)
	public List<Product> queryByName(String keyword) {
		List<Product> proList=new ArrayList<Product>();
		String sql = "select * from product where name like ?";
		// 1: 获取connection对象
		Connection conn = null;
		// 2: 预编译SQL语句 (解决SQL注入)
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			state = conn.prepareStatement(sql);
			// 3: 设置参数,执行SQL
			state.setString(1, "%" + keyword + "%");
			// 4: 执行SQL并且返回受影响行数,先执行finnally在返回
			rs = state.executeQuery();
			// 5: 从结果集中获取记录,然后转化为Java Model
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setRemark(rs.getString("remark"));
				product.setDate(rs.getDate("date"));
				proList.add(product);
			}
			return proList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("----finally----");
			// 5: 释放资源
			JdbcUtil.close(rs, state, conn);
		}
	}
}
