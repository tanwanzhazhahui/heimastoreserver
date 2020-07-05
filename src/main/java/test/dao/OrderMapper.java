package test.dao;

import test.pojo.Order;
import test.pojo.OrderProduct;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    List<Order> findOrder(Map map);
    Order findOrderWithProductByOid(String oid);
    void addOrder(Order order);
    void addOrderProduct(OrderProduct orderproduct);
}
