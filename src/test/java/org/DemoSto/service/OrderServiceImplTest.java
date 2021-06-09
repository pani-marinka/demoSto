package org.DemoSto.service;

import org.DemoSto.dao.RepositoryJra;
import org.DemoSto.dao.RepositoryJraForProduct;
import org.DemoSto.model.Order;
import org.DemoSto.model.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {

    @MockBean
    public ProductServiceImpl productServiceImpl;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private RepositoryJra repositoryJra;

    @MockBean
    private RepositoryJra repositoryJra1;

    @MockBean
    private RepositoryJraForProduct repositoryJraForProduct;



    @Test
    void createOrder() {

        Product product1 = new Product("1", "2", 24, 20.0f);
        Order order1 = new Order(1, "1", 2, new Date());
        Assert.assertTrue(orderServiceImpl.createOrder(order1, product1));

            }

    @Test
    void deleteOrderForAPI() {
        Date orderDate = new Date();
        int addMinuteTime = 15;
        final long ONE_MINUTE_IN_MILLIS = 60000;
        long curTimeInMs = orderDate.getTime();
        Date oldTime = new Date(curTimeInMs - (addMinuteTime * ONE_MINUTE_IN_MILLIS));

        Order order = new Order(1, "1", 2, oldTime);

        Order order1 = new Order(1, "1", 2, new Date());

        Mockito.doReturn(order1)
                .when(repositoryJra1)
                .getOne(order1.getOrderid());

        boolean isNotDeleteOrder = orderServiceImpl.deleteOrderForAPI(order1.getOrderid());
        Assert.assertFalse(isNotDeleteOrder);

        Mockito.doReturn(order)
                .when(repositoryJra1)
                .getOne(order.getOrderid());

        boolean isDeleteOrder = orderServiceImpl.deleteOrderForAPI(order.getOrderid());
        Assert.assertTrue(isDeleteOrder);
    }

    @Test
    void createSpezOrderAPI() {
        Product product1 = new Product("1", "2", 24, 20.0f);
        Order order = new Order();

        Mockito.doReturn(true)
                .when(productServiceImpl)
                .haveEnoughProducts("2", 3);

        Mockito.doReturn(product1)
                .when(productServiceImpl)
                .findProductById(product1.getProductid());

        Mockito.doReturn(true)
                .when(productServiceImpl)
                .reservedProduct(order.getProductid(), order.getQuantity());

        boolean isOrderCreated = orderServiceImpl.createOrderAPI(order);
        Assert.assertTrue(isOrderCreated);

    }

    @Test
    void createOrderAPI() {

        Product product1 = new Product("1", "2", 24, 20.0f);
        Order order1 = new Order(1, "1", 2, new Date());


        Mockito.doReturn(true)
                .when(productServiceImpl)
                .haveEnoughProducts("2", 3);

        Mockito.doReturn(product1)
                .when(productServiceImpl)
                .findProductById(product1.getProductid());

        Mockito.doReturn(true)
                .when(productServiceImpl)
                .reservedProduct(order1.getProductid(), order1.getQuantity());

        boolean isOrderCreated = orderServiceImpl.createOrderAPI(order1);
        Assert.assertTrue(isOrderCreated);

    }
}