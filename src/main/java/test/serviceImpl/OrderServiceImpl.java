package test.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.OrderMapper;
import test.pojo.Order;
import test.pojo.OrderProduct;
import test.service.OrderService;

import java.util.List;
import java.util.Map;

@Service("OrderService")
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findOrder(Map map) {
        return orderMapper.findOrder(map);
    }

    @Override
    public Order findOrderWithProductByOid(String oid) {
        return orderMapper.findOrderWithProductByOid(oid);
    }

    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    @Override
    public void addOrderProduct(OrderProduct orderproduct) {
        orderMapper.addOrderProduct(orderproduct);
    }
}
