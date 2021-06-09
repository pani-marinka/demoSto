package org.DemoSto.service;


import org.DemoSto.model.Product;

public interface ProductService {

    boolean haveEnoughProducts(String productid, Integer quantity);


    String findProductMinCostByName(String productName, Integer quantity);

    boolean reservedProduct(String productid, Integer quantity);

    void backProduct(String productid, Integer quantity);

    Product findProductById(String productid);
}
