package org.DemoSto.service;

import org.DemoSto.dao.RepositoryJraForProduct;
import org.DemoSto.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RepositoryJraForProduct repositoryJraForProduct;
    @Autowired
    private OrderService orderService;

    private List<Product> productList = new ArrayList<>();



    @Override
    public boolean productForOrder(String productid, Integer quantity) {  // removal product and check //*
        int y = 0;
        productList = repositoryJraForProduct.findAll();
        for (Product p : productList) {
            if (productid.equals(p.getProductid())) {
                y = p.getQuantity() - quantity;
                if ((y) > -1) {
                    p.setQuantity(y);
                    repositoryJraForProduct.save(p);
                    return true;  //if quantity >0
                }
            }
        }
        return false; //if quantity <0
    }



    private String findProductMinCostByName(String productName, Integer quantity) {
        String productid = null;
        float i = 0;
        List<Product> productList = repositoryJraForProduct.findAll();
        for (Product p : productList) {
            if (p.getProductname().equals(productName)) {
                if (p.getQuantity() >= quantity) {
                    if (i==0){i = p.getPrice(); productid = p.getProductid(); }
                    if (i>p.getPrice()) {i = p.getPrice(); productid = p.getProductid();} //todo for RESERVD?

                }
            }
        }
        return productid;
    }

    @Override
    public boolean createSpezOrderAPI(Product product) {
        String productId = findProductMinCostByName(product.getProductname(), product.getQuantity());
        Integer quantity = product.getQuantity();
        if (productId == null){
            return false;
        }
        if (productForOrder(productId,quantity) ){
            orderService.createSpezOrderAPI(productId, quantity);

        }
        return true;
    }

}

/*
 SELECT MIN(price)
FROM public.product
WHERE product.productname like 'pen'
 str = str.toLowerCase();
    return str.matches(expr);
    text.startsWith("app"); // like "app%"
text.endsWith("le"); // like "%le"
text.contains("ppl"); // like "%ppl%"
 */

