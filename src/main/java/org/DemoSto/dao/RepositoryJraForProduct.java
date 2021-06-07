package org.DemoSto.dao;

import org.DemoSto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryJraForProduct extends JpaRepository<Product, String> {
}
