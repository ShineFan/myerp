package com.example.order.service.impl;

import com.example.order.dao.OrderDao;
import com.example.order.model.Order;
import com.example.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RestTemplate restTemplate;

    public final static String ACCOUNT_SERVICE = "http://localhost:9001/account";
    public final static String STORAGE_SERVICE = "http://localhost:9002/storage";

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Order place(Order order) throws Exception{

        order.setAmount(order.getPrice() * order.getQuantity());
        order = orderDao.save(order);
        deducAccountBalance(order);
        deducStorageQuantity(order);
        return order;
    }

    private void deducAccountBalance(Order order) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> accountRequest = new LinkedMultiValueMap<>();
        accountRequest.add("id", order.getAccount());
        accountRequest.add("amount", order.getAmount());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(accountRequest, headers);
        restTemplate.postForEntity(ACCOUNT_SERVICE + "/deduc", request, Object.class);
    }

    private void deducStorageQuantity(Order order) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> storageRequest = new LinkedMultiValueMap<>();
        storageRequest.add("product", order.getProduct());
        storageRequest.add("quantity", order.getQuantity());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(storageRequest, headers);
        restTemplate.postForEntity(STORAGE_SERVICE + "/deduc", request, Object.class);
    }
}
