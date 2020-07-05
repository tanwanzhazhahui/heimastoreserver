package test.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.ProductMapper;
import test.pojo.Product;
import test.service.ProductService;

import java.util.List;
import java.util.Map;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findProduct(Map map) {
        return productMapper.findProduct(map);
    }

    @Override
    public List<Product> findProductLike(String productname) {
        return productMapper.findProductLike(productname);
    }

    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }
}
