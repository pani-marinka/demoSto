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


//    private static int nextValue() {
//        return counter.getAndIncrement();
//    }
//
//
//    @Override
//    public List<Order> getAllOrder() {
//        Order o = new Order();
//        o.setOrderid(nextValue());
//        o.setProductid("12");
//        o.setQuantity(1);
//        o.setOrderdate(new Date());
//        orderList.add(o);
//        return orderList;
//    }
//
//    @Override
//    public Order getById(Integer orderId) {
//
//        return repositoryJra.getOne(orderId);
//    }
//
//    @Override
//    public Order getByIdListOrder(Integer orderId) {
//        Order tmp = null;
//        for (Order o : orderList) {
//            if (o.getOrderid() == orderId) {
//                tmp = o;
//                return tmp;
//            }
//        }
//        return tmp;
//    }
//
//    @Override
//    public void deleteForTest(Integer orderId) {
//        Order tmp = null;
//        for (Order o : orderList) {
//            if (o.getOrderid() == orderId) {
//                tmp = o;
//            }
//
//        }
//        orderList.remove(tmp);
//    }
//
//    @Override
//    public void save(Order order) {
//        repositoryJra.save(order);
//    }
//
//    @Override
//    public List<Order> getAll() {
//        orderList = repositoryJra.findAll();
//        orderList.size();
//        return orderList;
//    }
//
//    @Override
//    public void delete(Integer orderId) {
//        repositoryJra.delete(getById(orderId));
//    }
//
//    @Override
//    //public void createOrder(Order order) {
//    public void createOrder(String param1) {
//
//        // boolean productCheckUpd = productService.productForOrder(order); //todo decrise store product
//        // if (productCheckUpd) {
//        Order o = new Order();
//        //Order o = order;
//        // o.setOrderid(nextValue());
//        o.setProductid(param1);
//        o.setQuantity(100);
//        o.setOrderdate(new Date());
//        orderList.add(o);
//        repositoryJra.save(o);//  userDao.add(u);
//    }
//
//
//    private boolean deleteOrderWithReturn(int orderid) { //delete with return item on the Store
//        Order tmp = null;
//        for (Order o : orderList) {
//            if (o.getOrderid() == orderid) {
//                tmp = o;
//            }
//        }
//        int addMinuteTime = 10; //10 min
//        Date nowTime = new Date();
//        final long ONE_MINUTE_IN_MILLIS = 60000;
//        Date orderDate = tmp.getOrderdate();
//        long curTimeInMs = orderDate.getTime();
//        Date afterAddingMins = new Date(curTimeInMs + (addMinuteTime * ONE_MINUTE_IN_MILLIS));
//        if (afterAddingMins.before(nowTime)) {
//            System.out.println("the Order  cannot be deleted");
//        } else {
//            productService.addProductAfterReturn(tmp.getProductid(), tmp.getQuantity());
//            orderList.remove(tmp);
//            return true; // all successfully
//        }
//        return false;
//    }

//
//    @Override
//    public boolean deleteOrder(Integer orderId) { //delete without return item
//        Order tmp = null;
//        for (Order o : orderList) {
//            if (o.getOrderid() == orderId) {
//                tmp = o;
//            }
//        }
//        int addMinuteTime = 10;
//        Date nowTime = new Date();
//        final long ONE_MINUTE_IN_MILLIS = 60000;
//        Date orderDate = tmp.getOrderdate();
//        long curTimeInMs = orderDate.getTime();
//        Date afterAddingMins = new Date(curTimeInMs + (addMinuteTime * ONE_MINUTE_IN_MILLIS));
//        if (afterAddingMins.before(nowTime)) {
//            System.out.println("the Order  cannot be deleted");
//        } else {
//            orderList.remove(tmp);
//            return true; // all successfully
//        }
//        return false;
//    }

//    @Override
//    //public String createOrderForMinCost(String productName, Integer quantity){
//    public String createOrderForMinCost(Order order) {
//        String productId = productService.findProductMinCostByName("test", order.getQuantity());
//        if (productId != null) {
//            //  createOrder(order); //createOrder(productId, quantity);
//
//            return productId;
//        }
//        return null;
//    }


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
    public void createOrderAPI(Order order) {
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
//
//       Date date = df.format(String.valueOf(order.getOrderdate()));
//       // String requiredDate = df.format(order.getOrderdate());

        boolean productCheckUpd = productService.productForOrder(order.getProductid(), order.getQuantity()); //todo decrise store product
        if (productCheckUpd) {
            Order o = new Order();
            o.setProductid(order.getProductid());
            o.setQuantity(order.getQuantity());
            o.setOrderdate(order.getOrderdate());
            repositoryJra.save(o);
        }
    }

}



