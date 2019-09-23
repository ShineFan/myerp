package com.example.storage.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Storage {
    @Id
    private Long id;
    private Long product;
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
