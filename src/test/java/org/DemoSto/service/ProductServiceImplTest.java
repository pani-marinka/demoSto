package org.DemoSto.service;

import org.DemoSto.dao.RepositoryJra;
import org.DemoSto.dao.RepositoryJraForProduct;
import org.DemoSto.model.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    public ProductServiceImpl productServiceImpl;

    @MockBean
    public ProductServiceImpl productServiceImpl1;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private RepositoryJraForProduct repositoryJraForProduct1;

//    @MockBean
//    private RepositoryJra repositoryJra;

    @MockBean
    private RepositoryJraForProduct repositoryJraForProduct;

    private Object Optional;


    @Test
    void reservedProduct() {
    }

    @Test
    void findProductById() {
        Product product = new Product();
        Product product1 = new Product("1", "2", 24, 20.0f);

        Mockito.doReturn(Optional)
                .when(repositoryJraForProduct1)
                .findById("1");
 }

    @Test
    void findProductMinCostByName() {
        Product product1 = new Product("1", "pen", 24, 20.0f);
        Product product2 = new Product("12", "pen", 24, 15.0f);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Mockito.doReturn(productList)
                .when(repositoryJraForProduct1)
                .findAll();

        Assert.assertEquals("12", productServiceImpl.findProductMinCostByName("pen", 2));
    }

    @Test
    void haveEnoughProducts() {

        Product product1 = new Product("1", "pen", 24, 20.0f);

        Mockito.doReturn(product1)
                .when(productServiceImpl1)
                .findProductById(product1.getProductid());


        Assert.assertTrue(productServiceImpl.haveEnoughProducts("1", 2));

    }

    @Test
    void backProduct() {
        Product product1 = new Product("1", "pen", 24, 20.0f);
        Mockito.doReturn(product1)
                .when(productServiceImpl1)
                .findProductById(product1.getProductid());
        productServiceImpl.backProduct("1",2);
        Assert.assertEquals(java.util.Optional.ofNullable(3), java.util.Optional.ofNullable(product1.getQuantity()));

        /*

        Product p = findProductById(productid);
        if (p != null) {
            int y = p.getQuantity() + quantity;
            p.setQuantity(y);
            repositoryJraForProduct.save(p);
        }
         */
    }
}