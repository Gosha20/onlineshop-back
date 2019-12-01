package com.baggage.repository;

import com.baggage.entity.dao.ClientDao;
import com.baggage.entity.dao.OrderDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDao, Integer> {
    OrderDao findByUserId(Integer userId);
}