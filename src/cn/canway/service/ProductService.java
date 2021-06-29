package cn.canway.service;

import cn.canway.model.Product;

import java.util.List;

public interface ProductService {

    int save(Product product);

    int update(Product product);

    int delete(int id);

    Product getById(int id);

    List<Product> queryByName(String keyword,int page,int size);
}
