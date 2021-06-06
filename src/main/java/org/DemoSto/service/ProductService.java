package org.DemoSto.service;

public interface ProductService {
    public void createProduct(String productname, Integer quantity, Float price);
    public void addProductAfterReturn(String productid, Integer quantity);
    public boolean productForOrder(String productid, Integer quantity);
    public String findProductMinCostByName(String productName, Integer quantity);
}
