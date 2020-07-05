package test.daoImpl;


import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import test.dao.ProductMapper;
import test.pojo.Product;

import java.util.List;
import java.util.Map;


public class ProductMapperImpl extends SqlSessionDaoSupport implements ProductMapper {

    @Override
    public List<Product> findProduct(Map map) {
        return getSqlSession().getMapper(ProductMapper.class).findProduct(map);
    }

    @Override
    public List<Product> findProductLike(String productname) {
        return getSqlSession().getMapper(ProductMapper.class).findProductLike(productname);
    }

    @Override
    public void addProduct(Product product) {
        getSqlSession().getMapper(ProductMapper.class).addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        getSqlSession().getMapper(ProductMapper.class).updateProduct(product);
    }
}
