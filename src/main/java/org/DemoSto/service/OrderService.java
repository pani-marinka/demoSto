package org.DemoSto.service;

import org.DemoSto.model.Order;
import org.DemoSto.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public boolean createOrderAPI(Order order);

    public boolean deleteOrderForAPI(Integer orderId);

    public boolean createSpezOrderAPI(Product product);
}
