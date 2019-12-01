package com.baggage.service.impl;

import com.baggage.entity.dao.OrderDao;
import com.baggage.entity.dao.ProductDao;
import com.baggage.repository.ProductRepository;
import com.baggage.service.OrderService;
import com.baggage.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    final ProductRepository productRepository;

    public OrderServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderDao> findAllByUserId(Integer userId) {
        return null;
    }

    @Override
    public OrderDao save(OrderDao orderDao) {
        return null;
    }

    @Override
    public void deleteFromOrder(Integer orderId) {

    }

    @Override
    public void appendInOrder(Integer userId, Integer productId) {

    }
}
