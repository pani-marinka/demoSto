package org.DemoSto.service;

import org.DemoSto.dao.RepositoryJra;
import org.DemoSto.dao.RepositorySto;
import org.DemoSto.data.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {


    private static final AtomicInteger counter = new AtomicInteger();
    private List<Order> orderList = new ArrayList<>();

    @Autowired
    private RepositoryJra repositoryJra;

    @Autowired
    private ProductService productService;


    private static int nextValue() {
        return counter.getAndIncrement();
    }


    @Override
    public List<Order> getAllOrder() {
        Order o = new Order();
        o.setOrderid(nextValue());
        o.setProductid("12");
        o.setQuantity(1);
        o.setOrderdate(new Date());
        orderList.add(o);
        return orderList;
    }

    @Override
    public Order getById(Integer orderId) {
        return repositoryJra.getOne(orderId);
    }


    @Override
    public void save(Order order) {
         repositoryJra.save(order);
    }

    @Override
    public List<Order> getAll() {
        return repositoryJra.findAll();
    }

    @Override
    public void delete(Integer orderId) {
        repositoryJra.delete(getById(orderId));
    }

    @Override
    public void createOrder(String productid, Integer quantity) {

        boolean productCheckUpd = productService.productForOrder(productid, quantity);
        if (productCheckUpd) {
            Order o = new Order();
            o.setOrderid(nextValue());
            o.setProductid(productid);
            o.setQuantity(quantity);
            o.setOrderdate(new Date());
            orderList.add(o);//  userDao.add(u);
        }
    }



    private boolean deleteOrderWithReturn(int orderid) { //delete with return item on the Store
        Order tmp = null;
        for (Order o : orderList) {
            if (o.getOrderid() == orderid) {
                tmp = o;
            }
        }
        int addMinuteTime = 10; //10 min
        Date nowTime = new Date();
        final long ONE_MINUTE_IN_MILLIS = 60000;
        Date orderDate = tmp.getOrderdate();
        long curTimeInMs = orderDate.getTime();
        Date afterAddingMins = new Date(curTimeInMs + (addMinuteTime * ONE_MINUTE_IN_MILLIS));
        if (afterAddingMins.before(nowTime)) {
            System.out.println("the Order  cannot be deleted");
        } else {
            productService.addProductAfterReturn(tmp.getProductid(), tmp.getQuantity());
            orderList.remove(tmp);
            return true; // all successfully
        }
            return false;
        }


    @Override
    public boolean deleteOrder(Integer orderId) { //delete without return item
        Order tmp = null;
        for (Order o : orderList) {
            if (o.getOrderid() == orderId) {
                tmp = o;
            }
        }
        int addMinuteTime = 10;
        Date nowTime = new Date();
        final long ONE_MINUTE_IN_MILLIS = 60000;
        Date orderDate = tmp.getOrderdate();
        long curTimeInMs = orderDate.getTime();
        Date afterAddingMins = new Date(curTimeInMs + (addMinuteTime * ONE_MINUTE_IN_MILLIS));
        if (afterAddingMins.before(nowTime)) {
            System.out.println("the Order  cannot be deleted");
        } else {
            orderList.remove(tmp);
            return true; // all successfully
        }
        return false;
    }

        @Override
    public String createOrderForMinCost(String productName, Integer quantity){
          String productId = productService.findProductMinCostByName(productName, quantity);
          if (productId != null){
              createOrder(productId, quantity);

              return productId;
          }
       return null;
    }

}


        /*getById
         public User getById(String id) {
        Session s = HibernateUtil.getSession();
        User u = (User) s.createQuery(String.format("FROM User WHERE id='%s'", id)).uniqueResult();
        s.close();
        return u;
         */

