package cn.canway.dao.impl;

import cn.canway.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    // main方法测试缺陷: 有侵入性. 不方便同时测试. Java建议采用Junit单元测试
    int save(Product product);

    int update(Product product);

    int delete(int id);

    Product getById(int id);

    List<Product> queryByName(Map paramMap);
}
