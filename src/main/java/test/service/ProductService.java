package test.service;

import test.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> findProduct(Map map);
    List<Product> findProductLike(String productname);
    void addProduct(Product product);
    void updateProduct(Product product);
}
