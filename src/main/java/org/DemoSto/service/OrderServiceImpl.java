package org.DemoSto.service;

import org.DemoSto.dao.RepositoryJra;
import org.DemoSto.model.Order;
import org.DemoSto.model.Product;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {



    @Autowired
    private RepositoryJra repositoryJra;

    @Autowired
    private ProductService productService;


    @Override
    public boolean deleteOrderForAPI(Integer orderId) { //delete without return item
        try {

            Order order = repositoryJra.getOne(orderId);
            int addMinuteTime = 10;
            Date nowTime = new Date();
            final long ONE_MINUTE_IN_MILLIS = 60000;
            Date orderDate = order.getOrderdate();
            long curTimeInMs = orderDate.getTime();
            Date afterAddingMins = new Date(curTimeInMs + (addMinuteTime * ONE_MINUTE_IN_MILLIS));
            if (afterAddingMins.after(nowTime)) {
                System.out.println("the Order  cannot be deleted");
                return false;
            } else {
                repositoryJra.delete(order);
                return true; // all successfully
            }
        } catch (javax.persistence.EntityNotFoundException ex) {
            return false;
        }
    }


    @Override
    public boolean createSpezOrderAPI(Product product) {
        String productId = productService.findProductMinCostByName(product.getProductname(), product.getQuantity());
        if (productId == null) {
            return false;
        }
        if (productService.reservedProduct(productId, product.getQuantity())) {
            Order order = new Order();
            order.setOrderdate(new Date());
            order.setQuantity(product.getQuantity());
            product.setProductid(productId);
            if (createOrder(order, product)) {
                return true;
            } else {
                productService.backProduct(productId, product.getQuantity());
                return false;
            }
        } else {
            return false;
        }
    }


      @Override
   public boolean createOrderAPI(Order order) {

          Product product = productService.findProductById(order.getProductid());
          if (productService.haveEnoughProducts(order.getProductid(), order.getQuantity())) {
              if (productService.reservedProduct(order.getProductid(), order.getQuantity())) {
                  try {
                      if (createOrder(order, product)) {
                          return true;
                      } else {
                          throw new Exception("Failed to create Order");
                      }
                  } catch (Exception e){
                      productService.backProduct(order.getProductid(), order.getQuantity());
                      return false;
                  }
              }return false;
          }
          return true;

    }


     boolean createOrder(Order order, Product product) {
        Order o = order;
        o.setProduct(product);
        repositoryJra.save(o);
        return true;
    }

}



