package org.DemoSto.dao;

import org.DemoSto.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryJra extends JpaRepository<Order, Integer> {
}
