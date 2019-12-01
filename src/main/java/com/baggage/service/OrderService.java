package com.baggage.service;


import com.baggage.entity.dao.ClientDao;
import com.baggage.entity.dao.OrderDao;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDao> findAllByUserId(Integer userId);
    OrderDao save(OrderDao orderDao);
    void deleteFromOrder(Integer orderId);
    void appendInOrder(Integer userId, Integer productId);
}
