package org.DemoSto.service;


import org.DemoSto.model.Product;

public interface ProductService {

    public boolean productForOrder(String productid, Integer quantity);
    public boolean createSpezOrderAPI(Product product);
}
