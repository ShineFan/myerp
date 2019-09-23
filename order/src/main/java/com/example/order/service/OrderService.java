package com.example.order.service;

import com.example.order.model.Order;

public interface OrderService {
    public Order place(Order order) throws Exception;
}
