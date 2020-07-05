package test.dao;

import test.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    List<Product> findProduct(Map map);
    List<Product> findProductLike(String productname);
    void addProduct(Product product);
    void updateProduct(Product product);
}
