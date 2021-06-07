package org.DemoSto.rest;

import org.DemoSto.model.Order;
import org.DemoSto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/orders/")
public class OrderControllerRestV1 {



    @Autowired
    private OrderService orderService;


//   // @RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
//    @GetMapping(value="{id}")
//    public ResponseEntity<Order> getOrder(@PathVariable("id") Integer orderId){
//        if (orderId == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Order order = this.orderService.getByIdListOrder(orderId);
//        if (order == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(order, HttpStatus.OK);
//    }

//    @GetMapping
//    public ResponseEntity<List<Order>> getAll(){
//     // List<Order> orders =this.orderService.getAllOrder();
//        List<Order> orders =this.orderService.getAll(); //byDB
//
////      if(orders.isEmpty()){
////          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////      }
//      return new ResponseEntity<>(orders,HttpStatus.OK);
//    }


    //@RequestMapping(value="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
//   @GetMapping(value="/all")
//    public ResponseEntity<List<Order>>  getAllOrders(){
//        List<Order> orders =this.orderService.getAllOrder(); //byList
//
//        if(orders.isEmpty()){
//          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//      }
//      return  new ResponseEntity<>(orders,HttpStatus.OK);
//    }


   @DeleteMapping(value="{id}")
   public ResponseEntity<Order> deleteOrder(@PathVariable("id") Integer orderId) {
       if (orderId == null) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       this.orderService.deleteOrderForAPI(orderId);
       return new ResponseEntity<>( HttpStatus.OK);
   }
/*
fetch('/api/v1/orders/1', {method:'DELETE'}).then(result=>console.log(result))
 */

//    @PostMapping
//    //public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//    public ResponseEntity<Order> createOrder(@RequestParam(value="params", required = false) String params) {
////        if (order == null) {
////            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////        }
//        this.orderService.createOrder(params);
//        return new ResponseEntity<>( HttpStatus.OK);
//    }

/*
fetch('/api/v1/orders/', { method: 'POST',headers: {'Accept': 'application/json','Content-Type': 'application/json'},body: JSON.stringify({mess: "myName"})}).then(result=>console.log(result))
fetch('/api/v1/orders/', { method: 'POST',headers: {'Accept': 'application/json','Content-Type': 'application/json'},body: JSON.stringify({"orderid":2,"productid":"12","quantity":1,"orderdate":"2021-06-07T06:02:13.352+00:00"})}).then(result=>console.log(result))
-fetch('/api/v1/orders?param=mnp/', { method: 'POST',headers: {'Accept': 'application/json','Content-Type': 'application/json'},body: JSON.stringify({"productid":"12","quantity":1,"orderdate":"2021-06-07T06:02:13.352+00:00"})}).then(result=>console.log(result))
-http://localhost:8080/api/v1/orders?param=mnp/
 */
}
