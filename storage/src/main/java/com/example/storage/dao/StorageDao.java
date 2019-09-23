package com.example.storage.dao;

import com.example.storage.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageDao extends JpaRepository<Storage, Long> {
    public Storage findByProduct(Long product);
}
