package com.baggage.entity.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order")
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrderDao implements Serializable {

    @Id
    @JsonIgnore
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    public OrderDao(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    @Column(name = "product_id")
    private Integer productId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public OrderDao() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}