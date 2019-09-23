package com.example.storage.service.impl;

import com.example.storage.dao.StorageDao;
import com.example.storage.model.Storage;
import com.example.storage.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao storageDao;
    @Override
    public void deduc(Long product, Long quantity) throws Exception{
       Storage storage = storageDao.findByProduct(product);
       Long storageQuantity = storage.getQuantity();
       if (storageQuantity < quantity) {
           throw new Exception("库存不足");
       }
       storage.setQuantity(storageQuantity - quantity);
       storageDao.save(storage);
    }
}
