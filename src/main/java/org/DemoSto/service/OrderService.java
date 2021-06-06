package org.DemoSto.service;

import org.DemoSto.data.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public void createOrder(String productid, Integer quantity);

    public boolean deleteOrder(Integer orderId);

    public String createOrderForMinCost(String productName, Integer quantity);

    public List<Order> getAllOrder();

    Order getById(Integer orderId);

    void save(Order order);

    List<Order> getAll();

    void delete(Integer orderId);


}
