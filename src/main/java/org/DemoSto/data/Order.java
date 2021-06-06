package org.DemoSto.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "public.order")
public class Order implements Serializable {
    @Id
    @Column(name = "orderid")
    private Integer orderid;
    @Column(name = "productid")
    private String productid;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "orderdate")
    private Date orderdate;

    public Order() {
    }

    public Order(Integer orderid, String ordername, String productid, Integer quantity, Float price, Date orderdate) {
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
        this.orderdate = orderdate;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderid, order.orderid) && Objects.equals(productid, order.productid) && Objects.equals(quantity, order.quantity) && Objects.equals(orderdate, order.orderdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderid, productid, quantity, orderdate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", productid='" + productid + '\'' +
                ", quantity=" + quantity +
                ", orderdate=" + orderdate +
                '}';
    }
}

