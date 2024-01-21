package com.example.ecommerce.orderservice.models;


import lombok.Setter;

@Setter
public class Order {
    private Integer id;
    private String itemName;
    private Integer itemQuantity;

    public Integer getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }
}
