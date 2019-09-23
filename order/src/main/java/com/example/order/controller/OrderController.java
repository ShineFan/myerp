package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> place (@RequestBody Order order) throws Exception{
        ResponseEntity<Order> orderResponseEntity = new ResponseEntity<>(HttpStatus.OK);
        orderService.place(order);
        return orderResponseEntity;
    }
}
