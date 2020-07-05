package test.daoImpl;


import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import test.dao.OrderMapper;
import test.pojo.Order;
import test.pojo.OrderProduct;

import java.util.List;
import java.util.Map;


public class OrderMapperImpl extends SqlSessionDaoSupport implements OrderMapper {
    @Override
    public List<Order> findOrder(Map map) {
        return getSqlSession().getMapper(OrderMapper.class).findOrder(map);
    }

    @Override
    public Order findOrderWithProductByOid(String oid) {
        return getSqlSession().getMapper(OrderMapper.class).findOrderWithProductByOid(oid);
    }

    @Override
    public void addOrder(Order order) {
        getSqlSession().getMapper(OrderMapper.class).addOrder(order);
    }

    @Override
    public void addOrderProduct(OrderProduct orderproduct) {
        getSqlSession().getMapper(OrderMapper.class).addOrderProduct(orderproduct);
    }
}
