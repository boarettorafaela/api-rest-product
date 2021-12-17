package com.project.pagbank.controller;

import com.project.pagbank.controller.dto.ProductDto;
import com.project.pagbank.controller.form.ProductForm;
import com.project.pagbank.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RequestMapping ("/products")
@RestController
@Api(value = "Product Controller")
public class ProductController {

	@Autowired
	private ProductService service;

	@ApiOperation(value = "List the products")
	@GetMapping
	public Page<ProductDto> pageProducts(@PageableDefault(sort = "price",
			direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable ){
		return service.list(pageable);
	}

	@ApiOperation(value = "Search products by ID")
	@GetMapping("/{id}")
	public ProductDto findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Create a product")
	@PostMapping
	public ResponseEntity<ProductDto> saveProduct (@RequestBody ProductForm productForm, UriComponentsBuilder uriBuilder) {
		return service.saveProduct(productForm, uriBuilder);
	}

	@ApiOperation(value = "Update product")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid ProductForm form) {
		return service.update(id, form);
		}

	@ApiOperation(value = "Delete product")
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return service.delete(id);
	}

	@ApiOperation(value = "Search products with filters")
	@GetMapping("/search")
	public Page<ProductDto> search(
			@PageableDefault(sort = "price", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable,
			@RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) String q) {

		return service.search(pageable, maxPrice, minPrice, q);
			//return ProductDto.convert(products);
	}


}












