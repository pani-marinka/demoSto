package org.DemoSto.service;

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


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    public ProductServiceImpl productServiceImpl;

    @Autowired
    private RepositoryJraForProduct repositoryJraForProduct1;

    @MockBean
    private RepositoryJraForProduct repositoryJraForProduct;

    private Object Optional;


    @Test
    void reservedProduct() {
    }

    @Test
    void findProductById() {
        Optional<Product> product = java.util.Optional.of(new Product("1","test", 12, 12f));


                Mockito.doReturn(product)
                .when(repositoryJraForProduct)
                .findById(product.get().getProductid());

            Product product1 = productServiceImpl.findProductById("1");
           Assert.assertArrayEquals(new String[]{product.get().getProductid()}, new String[]{product1.getProductid()});

 }

    @Test
    void findProductMinCostByName() {
        Product product1 = new Product("1", "pen", 24, 20.0f);
        Product product2 = new Product("12", "pen", 24, 15.0f);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Mockito.doReturn(productList)
                .when(repositoryJraForProduct)
                .findAll();

        String id = productServiceImpl.findProductMinCostByName("pen", 24);
        //Assert.assertEquals(id,"12");
        Assert.assertEquals(id,"1");
         }

    @Test
    void haveEnoughProducts() {

        Optional<Product> product = java.util.Optional.of(new Product("1","test", 12, 12f));
       Mockito.doReturn(product)
                .when(repositoryJraForProduct)
                .findById(product.get().getProductid());

        Product product2 = productServiceImpl.findProductById("1");

      Assert.assertTrue(productServiceImpl.haveEnoughProducts(product2.getProductid(), 2));
        Assert.assertFalse(productServiceImpl.haveEnoughProducts(product2.getProductid(), 22));



    }

    @Test
    void backProduct() {
        Optional<Product> product = java.util.Optional.of(new Product("1","test", 12, 12f));
        Mockito.doReturn(product)
                .when(repositoryJraForProduct)
                .findById(product.get().getProductid());

        Product product2 = productServiceImpl.findProductById("1");


        productServiceImpl.backProduct(product2.getProductid(),2);
        int i = product2.getQuantity();
        Assert.assertEquals(14,i);

    }
}