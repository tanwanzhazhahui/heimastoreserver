package test.service;

import test.pojo.Order;
import test.pojo.OrderProduct;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> findOrder(Map map);
    Order findOrderWithProductByOid(String oid);
    void addOrder(Order order);
    void addOrderProduct(OrderProduct orderproduct);
}
