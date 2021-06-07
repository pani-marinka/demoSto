package org.DemoSto.service;


import org.DemoSto.model.Product;

public interface ProductService {
//    public void createProduct(String productname, Integer quantity, Float price);
//    public void addProductAfterReturn(String productid, Integer quantity);
    public boolean productForOrder(String productid, Integer quantity);
    public boolean createSpezOrderAPI(Product product);
}
