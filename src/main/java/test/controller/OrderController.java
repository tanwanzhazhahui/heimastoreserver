package test.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.pojo.Order;
import test.pojo.OrderProduct;
import test.pojo.User;
import test.service.OrderService;
import test.service.UserService;
import test.utils.OrderIdUtils;
import test.utils.jsonvo.JsonOrder;
import test.utils.jsonvo.JsonOrderList;
import test.utils.jsonvo.JsonProductList;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/order",produces="application/json;charset=UTF-8")
public class OrderController {

    @Autowired@Qualifier("OrderService")
    private OrderService orderService;

    @Autowired@Qualifier("UserService")
    private UserService userService;

    @PostMapping(path = "/createOrder")
    private String createOrder(Double order_price,String consignee_addr,String wx_id,String product){
        List<JsonProductList> jsonProducts = JSON.parseArray(product, JsonProductList.class);

        //创建订单
        HashMap umap = new HashMap();
        umap.put("wx_id",wx_id);
        User user = userService.findUser(umap).get(0);

        String oid= OrderIdUtils.createID();
        SimpleDateFormat formatter= new SimpleDateFormat( "yyyy-MM-dd '的' HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());

        Order order = new Order();
        order.setOid(oid);
        order.setPrice(order_price);
        order.setAddress(consignee_addr);
        order.setTime(formatter.format(date));
        order.setStatus("未付款");
        order.setUserid(user.getId());

        orderService.addOrder(order);

        for (JsonProductList jsonProductList : jsonProducts) {
            OrderProduct orderproduct = new OrderProduct();
            orderproduct.setOrderid(order.getId());
            orderproduct.setProductid(jsonProductList.getProductimage_id());
            orderproduct.setNumber(jsonProductList.getProduct_number());
            orderService.addOrderProduct(orderproduct);
        }

        return "订单号:"+oid;
    }

    @PostMapping(path = "/getOrderByTypeAndWx_id")
    private @ResponseBody Map getOrderByTypeAndWx_id(String wx_id,String type){
        HashMap map = new HashMap();
        User user = userService.findUserWithOrderByWx_id(wx_id);
        JsonOrderList jsonOrderList=new JsonOrderList();
        List<JsonOrder> orderList=new ArrayList<>();
        for (Order userOrder : user.getOrders()) {
            JsonOrder jsonOrder = new JsonOrder();
            jsonOrder.setId(userOrder.getId());
            jsonOrder.setOid(userOrder.getOid());
            jsonOrder.setUser_id(userOrder.getUserid());
            jsonOrder.setOrder_price(userOrder.getPrice());
            jsonOrder.setTime(userOrder.getTime());

            orderList.add(jsonOrder);
        }
        jsonOrderList.setOrders(orderList);
        jsonOrderList.setCount(user.getOrders().size());

        map.put("订单",jsonOrderList);
        return map;
    }

}
