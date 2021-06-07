package org.DemoSto.service;

import org.DemoSto.dao.RepositoryJra;
import org.DemoSto.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {


    private static final AtomicInteger counter = new AtomicInteger();
    private List<Order> orderList = new ArrayList<>();

    @Autowired
    private RepositoryJra repositoryJra;

    @Autowired
    private ProductService productService;


    @Override
    public boolean deleteOrderForAPI(Integer orderId) { //delete without return item
        Order order = repositoryJra.getOne(orderId);
        if (order == null) return false;
        int addMinuteTime = 10;
        Date nowTime = new Date();
        final long ONE_MINUTE_IN_MILLIS = 60000;
        Date orderDate = order.getOrderdate();
        long curTimeInMs = orderDate.getTime();
        Date afterAddingMins = new Date(curTimeInMs + (addMinuteTime * ONE_MINUTE_IN_MILLIS));
        if (afterAddingMins.after(nowTime)) {
            System.out.println("the Order  cannot be deleted");
        } else {
            repositoryJra.delete(order);
            return true; // all successfully
        }
        return false;
    }


    @Override
    public void createSpezOrderAPI(String productid, Integer quantity) {
            Order o = new Order();
            o.setProductid(productid);
            o.setQuantity(quantity);
            o.setOrderdate(new Date());
            repositoryJra.save(o);

    }

    @Override
    public boolean createOrderAPI(Order order) {
        boolean productCheckUpd = productService.productForOrder(order.getProductid(), order.getQuantity()); //todo decrise store product
        if (productCheckUpd) {
            Order o = new Order();
            o.setProductid(order.getProductid());
            o.setQuantity(order.getQuantity());
            o.setOrderdate(order.getOrderdate());
            repositoryJra.save(o);
            return true;
        }
        return false;
    }

}



