package org.DemoSto.rest;

import org.DemoSto.data.Order;
import org.DemoSto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders/")
public class OrderControllerRestV1 {



    @Autowired
    private OrderService orderService;

    @RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") Integer orderId){
        if (orderId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
         Order order = this.orderService.getById(orderId);
        if (order == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ResponseEntity<List<Order>> getAllOrders(){
      List<Order> orders =this.orderService.getAllOrder();

      if(orders.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(HttpStatus.OK);
    }

//    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
//    public List<Order> getAllOrders(){
//        List<Order> orders =this.orderService.getAllOrder();
//
//        if(orders.isEmpty()){
//          return new ArrayList<Order>();
//      }
//      return new ArrayList<Order>();
//    }


}
