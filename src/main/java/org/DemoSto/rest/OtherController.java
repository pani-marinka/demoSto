package org.DemoSto.rest;

import org.DemoSto.model.Order;
import org.DemoSto.model.Product;
import org.DemoSto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spezorder/")
public class OtherController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<Order> createSpezOrderForAPI(@RequestBody Product product) {
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (this.productService.createSpezOrderAPI(product)) {

        return new ResponseEntity<>(HttpStatus.OK);
    }else{
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

/*
fetch('/api/spezorder/', { method: 'POST',headers: {'Accept': 'application/json','Content-Type': 'application/json'},body: JSON.stringify({"productname":"pen","quantity":5})}).then(result=>console.log(result))
 */
}
