package org.DemoSto.dao;

import org.DemoSto.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryJra extends JpaRepository<Order, Integer> {
}
