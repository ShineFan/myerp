package com.example.account.controller;

import com.example.account.model.Account;
import com.example.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/deduc")
    public ResponseEntity<Account> deduc(@RequestParam Long id, @RequestParam Double amount) {
        ResponseEntity<Account> accountResponseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            accountService.deduc(id, amount);
        } catch (Exception e) {
            accountResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return accountResponseEntity;
    }
}
