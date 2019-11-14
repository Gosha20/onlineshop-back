package com.baggage.service.impl;

import com.baggage.entity.dao.ProductDao;
import com.baggage.repository.ProductRepository;
import com.baggage.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<ProductDao> findAll() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            logger.error("cant take all product " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProductDao> findByName(String name) {
        try {
            return productRepository.findAllByName(name);
        } catch (Exception e) {
            logger.error("cant take product by name " + name);
            return new ArrayList<>();
        }
    }

    @Override
    public ProductDao save(ProductDao productDao) {
        try {
            return productRepository.save(productDao);
        } catch (Exception e) {
            logger.error("Failed save to database " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<ProductDao> findById(Integer id) {
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            logger.error("Failed find product by id " + e.getMessage());
            return Optional.empty();
        }
    }
}
