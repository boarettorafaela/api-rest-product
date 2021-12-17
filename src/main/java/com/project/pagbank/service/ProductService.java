package com.project.pagbank.service;

import com.project.pagbank.controller.dto.ProductDto;
import com.project.pagbank.controller.form.ProductForm;
import com.project.pagbank.model.Product;
import com.project.pagbank.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Page<ProductDto> list(Pageable pageable) {
        Page<Product> product = repository.findAll(pageable);
        return ProductDto.convert(product);
    }

    public ProductDto findById(Long id) {
        Product product = repository.getById(id);
        return new ProductDto(product);
    }

    public ResponseEntity<ProductDto> saveProduct (ProductForm productForm, UriComponentsBuilder uriBuilder) {
        Product product = productForm.convert(repository);
        repository.save(product);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }

    public ResponseEntity<ProductDto> update (Long id,ProductForm form) {
        Product product = form.update(id, repository);
        return ResponseEntity.ok(new ProductDto(product));
    }

    public ResponseEntity<?> delete(Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Page<ProductDto> search(Pageable pageable, Double maxPrice, Double minPrice, String q) {
        Page<Product> products = repository.findBySearch(pageable, q, minPrice, maxPrice);
        return ProductDto.convert(products);
    }

}
