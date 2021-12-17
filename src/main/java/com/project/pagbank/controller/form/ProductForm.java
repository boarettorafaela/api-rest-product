package com.project.pagbank.controller.form;


import com.project.pagbank.controller.dto.ProductDto;
import com.project.pagbank.model.Product;
import com.project.pagbank.repository.ProductRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class ProductForm {
    @NotNull @NotEmpty @Length(min = 3, max = 20)
    private String name;
    @NotNull @NotEmpty @Length(min = 3, max = 30)
    private String description;
    @NotNull @DecimalMin(value = "1.0")
    private Double price;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Product update(Long id, ProductRepository repository) {
        Product product = repository.getById(id);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        return product;
    }

    public Product convert(ProductRepository repository) {
        return new Product(name, description, price);

    }

}
