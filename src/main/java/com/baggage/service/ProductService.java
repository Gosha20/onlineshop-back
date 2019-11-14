package com.baggage.service;


import com.baggage.entity.dao.ProductDao;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDao> findAll();
    List<ProductDao> findByName(String name);
    ProductDao save(ProductDao productDao);
    Optional<ProductDao> findById(Integer id);
}
