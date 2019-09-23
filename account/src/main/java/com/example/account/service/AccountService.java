package com.example.account.service;

import com.example.account.model.Account;

public interface AccountService {
    public void deduc(Long id, Double amount) throws Exception;
}
