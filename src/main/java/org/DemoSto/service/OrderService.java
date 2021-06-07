package org.DemoSto.service;

import org.DemoSto.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public void createOrderAPI(Order order);
    public boolean deleteOrderForAPI(Integer orderId);
    public void createSpezOrderAPI(String productid, Integer quantity);

    //public boolean createSpezOrderAPI(Product product);



//    public void createOrder(String param1);
//
//    public boolean deleteOrder(Integer orderId);

    //public String createOrderForMinCost(String productName, Integer quantity);
   // public String createOrderForMinCost(Order order);

//    public List<Order> getAllOrder();
//
//    Order getById(Integer orderId);
//
//    void save(Order order);
//
//    public List<Order> getAll();
//
//    void delete(Integer orderId);
//
//
//    public Order getByIdListOrder(Integer orderId);
//    public void deleteForTest(Integer orderId);


}
