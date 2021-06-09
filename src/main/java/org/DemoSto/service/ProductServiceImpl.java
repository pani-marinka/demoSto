package org.DemoSto.service;

import org.DemoSto.dao.RepositoryJraForProduct;
import org.DemoSto.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RepositoryJraForProduct repositoryJraForProduct;



    @Override
    public boolean reservedProduct(String productid, Integer quantity) {
        Product p = findProductById(productid);
        if (p != null) {
            int y = p.getQuantity() - quantity;
            if ((y) > -1) {
                p.setQuantity(y);
                repositoryJraForProduct.save(p);
                return true;  //if quantity >0
            }
        }
        return false; //if quantity <0
    }


    public Product findProductById(String productid) {
        return repositoryJraForProduct.findById(productid).orElse(null);
    }


    public String findProductMinCostByName(String productName, Integer quantity) {
        String productid = null;
        float i = 0;
        List<Product> productList = repositoryJraForProduct.findAll();
        for (Product p : productList) {
            if (p.getProductname().equals(productName)) {
                if (p.getQuantity() >= quantity) {
                    if (i == 0) {
                        i = p.getPrice();
                        productid = p.getProductid();
                    }
                    if (i > p.getPrice()) {
                        i = p.getPrice();
                        productid = p.getProductid();
                    }

                }
            }
        }
        return productid;
    }


    @Override
    public boolean haveEnoughProducts(String productid, Integer quantity) {
        Product p = findProductById(productid);
        if (p != null) {
            int y = p.getQuantity() - quantity;
            if ((y) > -1) {
                return true;
            }
        }
        return false; //if quantity <0
    }

    @Override
    public void backProduct(String productid, Integer quantity) {

        Product p = findProductById(productid);
        if (p != null) {
            int y = p.getQuantity() + quantity;
            p.setQuantity(y);
            repositoryJraForProduct.save(p);
        }
    }

}


