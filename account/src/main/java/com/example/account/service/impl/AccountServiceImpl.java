package com.example.account.service.impl;

import com.example.account.dao.AccountDao;
import com.example.account.model.Account;
import com.example.account.service.AccountService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Override
    public void deduc(Long id, Double amount) throws Exception{
        Account account = accountDao.getOne(id);
        Double balance = account.getBalance();
        if (balance < amount) {
            throw new Exception("余额不足");
        }

        account.setBalance(balance - amount);

        accountDao.save(account);
    }
}
