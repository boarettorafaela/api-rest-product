package com.project.pagbank.controller.dto;

import com.project.pagbank.model.Product;

public class DetailsProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;

    public DetailsProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }

}
