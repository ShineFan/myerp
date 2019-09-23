package com.example.storage.controller;

import com.example.storage.model.Storage;
import com.example.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/deduc")
    public ResponseEntity<Storage> deduc(@RequestParam Long product, @RequestParam Long quantity) {
        ResponseEntity<Storage> storageResponseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            storageService.deduc(product, quantity);
        } catch (Exception e) {
            storageResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return storageResponseEntity;
    }
}
