package cn.canway.dao.impl;

import cn.canway.model.Product;
import cn.canway.util.JdbcUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
// 反射(运行时), 框架100% 使用反射增加灵活性
// 所有访问数据库的公共类
public abstract class BaseDaoImpl<T> {

    // 如果有些功能不同的子类实现不同,则定义抽象方法
    protected abstract T getRow(ResultSet rs) throws SQLException;

    public List<T> queryByName(String sql, Object keyword) {
        List<T> tList=new ArrayList<T>();
        // 1: 获取connection对象
        Connection conn = null;
        // 2: 预编译SQL语句 (解决SQL注入)
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            state = conn.prepareStatement(sql);
            // 3: 设置参数,执行SQL
            state.setObject(1, "%" + keyword + "%");
            // 4: 执行SQL并且返回受影响行数,先执行finnally在返回
            rs = state.executeQuery();
            // 5: 从结果集中获取记录,然后转化为Java Model
            while (rs.next()) {
                tList.add(this.getRow(rs));
            }
            return tList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("----finally----");
            // 5: 释放资源
            JdbcUtil.close(rs, state, conn);
        }
    }
    // Object
    protected T getById(String sql, Object id,Class clazz) {
        T model = null;
        // 1: 获取connection对象
        Connection conn = null;
        // 2: 预编译SQL语句 (解决SQL注入)
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            state = conn.prepareStatement(sql);
            // 3: 设置参数,执行SQL
            state.setObject(1, id);
            // 4: 执行SQL并且返回受影响行数,先执行finnally在返回
            rs = state.executeQuery();
            // 5: 从结果集中获取记录,然后转化为Java Model
            if (rs.next()) { // 说明有结果集,因此会创建一个对象
                model = (T)clazz.newInstance();
                // 获取结果集元数据信息
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i=1;i<=rsmd.getColumnCount();i++){
                    String colName = rsmd.getColumnName(i);
                    // 根据列的名称动态获取当前Class文件对应的字段
                    Field field =clazz.getDeclaredField(colName);
                    // 属性.set(对象,值)
                    field.setAccessible(true);
                    field.set(model,rs.getObject(colName));
                }
            }
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("----finally----");
            // 5: 释放资源
            JdbcUtil.close(rs, state, conn);
        }
    }
    // 支持所有数据库表的更新操作
    protected int update(String sql,Object[] param){
        // 1: 获取connection对象
        Connection conn = null;
        // 2: 预编译SQL语句 (解决SQL注入)
        PreparedStatement state = null;
        try {
            conn = JdbcUtil.getConnection();
            state = conn.prepareStatement(sql);
            for(int i=1;i<=param.length;i++){
                state.setObject(i, param[i-1]);
            }
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
}
