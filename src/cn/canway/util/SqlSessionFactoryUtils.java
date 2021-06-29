package cn.canway.util;

import cn.canway.dao.impl.ProductDaoImpl;
import cn.canway.model.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;

// SqlSessionFactory: 工厂模式,创建一个SqlSession(Connection)
public class SqlSessionFactoryUtils {
    public static void main(String[] args) {
        String resource = "/mybatis-config.xml";
        InputStream inputStream =  SqlSessionFactoryUtils.class.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 完成数据库的CRUD操作, 默认MyBatis自身采用手动提交
        Product product = new Product(null,"mybatis demo",new BigDecimal(1000),"我是备注....");
        try{
            sqlSession.insert(ProductDaoImpl.class.getName() + ".save",product);
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }

    }
}
