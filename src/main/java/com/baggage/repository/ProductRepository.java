package com.baggage.repository;

import com.baggage.entity.dao.ClientDao;
import com.baggage.entity.dao.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductDao, Integer> {
    List<ProductDao> findAllByName(String name);
}