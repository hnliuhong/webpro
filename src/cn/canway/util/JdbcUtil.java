package cn.canway.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 获取数据库驱动、创建、销毁连接的工具类
public class JdbcUtil {

    // 1: 加载驱动 (有且只加载一次),可以放在静态块
    static {
        // 根据类全名加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(); // 抛出异常
        }
    }

    // 根据数据用户名,密码返回Connection
    // 2: 获取数据链接信息,返回Connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 3: 关闭连接
    public static void close(ResultSet rs, Statement state, Connection conn) {

        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (state != null && !state.isClosed()) {
                    state.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(JdbcUtil.getConnection());
        System.out.println(JdbcUtil.getConnection());
    }
}
