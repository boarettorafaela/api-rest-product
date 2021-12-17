package com.project.pagbank.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.project.pagbank.model.Product;
import org.springframework.data.domain.Page;

public class ProductDto {

	private String description;
	private Long id;
	private String name;
	private Double price;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
	
    public static Page<ProductDto> convert(Page<Product> product) {
		return product.map(ProductDto :: new);
    }
	
}
