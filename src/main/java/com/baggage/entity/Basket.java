package com.baggage.entity;

import com.baggage.entity.dao.ProductDao;

import java.util.List;
import java.util.Optional;

public class Basket {
    private List<ProductDao> products;
    private Integer size;

    public Basket(List<ProductDao> products) {
        this.products = products;
        this.size = products.size();
    }

    public List<ProductDao> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDao> products) {
        this.products = products;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
