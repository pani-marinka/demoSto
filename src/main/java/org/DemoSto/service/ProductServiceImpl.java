package org.DemoSto.service;

import org.DemoSto.data.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private List<Product> productList = new ArrayList<>();

    @Override
    public void createProduct(String productname, Integer quantity, Float price) {
        Product p = new Product();
        p.setProductid(UUID.randomUUID().toString());
        p.setProductname(productname);
        p.setQuantity(quantity);
        p.setPrice(price);
        productList.add(p); //        userDao.add(u);
    }


    @Override
    public void addProductAfterReturn(String productid, Integer quantity) {  // add product after return
        // getById
        /*
         public User getById(String id) {
        Session s = HibernateUtil.getSession();
        User u = (User) s.createQuery(String.format("FROM User WHERE id='%s'", id)).uniqueResult();
        s.close();
        return u;
         */

        for (Product p : productList) {
            if (productid == p.getProductid()) {
                p.setQuantity(p.getQuantity() + quantity);
            }
        }
    }

    @Override
    public boolean productForOrder(String productid, Integer quantity) {  // removal product and check
        int y = 0;
        for (Product p : productList) {
            if (productid == p.getProductid()) {
                y = p.getQuantity() - quantity;
                if ((y) > -1) {
                    p.setQuantity(y);
                    return true;  //if quantity >0
                }
            }
        }
        return false; //if quantity <0
    }


//    @Override
//    public boolean deleteProductForOrder(String productid, Integer quantity) {  //removal after delivery
//        int x = 0;
//        for (Product p : productList) {
//            if (productid == p.getProductid()) {
//                x = p.getQuantity() - quantity;
//                if ((x) > -1) {
//                    p.setQuantity(x);
//                    return true;  //if quantity >0
//                }
//            }
//        }
//        return false; //if quantity <0
//    }

    @Override
    public String findProductMinCostByName(String productName, Integer quantity) {
        String productid = null;
        List<Product> tmp = new ArrayList<>();
        for (Product p : productList) {
            if (p.getProductname().equals(productName)) {
                if ( p.getQuantity() >= quantity ){
                 tmp.add(p);
                }
            }
        }

        if (tmp.size() > 1) {
            float i = 0;
            for (Product p : tmp) {
                if (i == 0) {
                    i = p.getPrice();
                }
                if (p.getPrice() < i) productid = p.getProductid();
            }
        } else {
            for (Product p : tmp) {
                productid = p.getProductid();
            }
        }
        return productid;
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
}
