package org.DemoSto.dao;


import org.DemoSto.data.Order;
import org.springframework.data.repository.CrudRepository;

public interface RepositorySto extends CrudRepository<Order, Long> {

}