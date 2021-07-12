package org.DemoSto.rest;

import org.DemoSto.model.Order;
import org.DemoSto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/")
public class ResController {

    @Autowired
    private OrderService orderService;


    @DeleteMapping(value="{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") Integer orderId) {
        if (orderId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
       if (this.orderService.deleteOrderForAPI(orderId)) {
        return new ResponseEntity<>( "DELETE",HttpStatus.OK);}else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

    }
/*
fetch('/api/order/1', {method:'DELETE'}).then(result=>console.log(result))
for Scratch:
DELETE http://localhost:8080/api/order/1
 */

    @PostMapping
    public ResponseEntity createOrderForAPI(@RequestBody Order order) {
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (this.orderService.createOrderAPI(order)) {;
        return new ResponseEntity<>(order, HttpStatus.OK);

        }else {
            return new ResponseEntity<>("NOT CREATED", HttpStatus.BAD_REQUEST);
        }

    }

/*
for Chrome:
fetch('/api/order/', { method: 'POST',headers: {'Accept': 'application/json','Content-Type': 'application/json'},body: JSON.stringify({"productid":"1","quantity":1,"orderdate":"2021-06-07T06:02:13.352+00:00"})}).then(result=>console.log(result))

for Scratch:
POST http://localhost:8080/api/order/
Accept: application/json
Content-Type: application/json

{
  "productid":"1",
  "quantity":2,
  "orderdate":"2021-06-07T06:02:13.352+00:00"
}
 */
}
