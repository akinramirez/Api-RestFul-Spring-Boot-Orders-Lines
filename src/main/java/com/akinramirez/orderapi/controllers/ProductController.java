package com.akinramirez.orderapi.controllers;

import java.util.*;

import javax.management.RuntimeErrorException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akinramirez.orderapi.entity.*;

@RestController
public class ProductController {
	private List<Product> products = new ArrayList<>();

	public ProductController() {
		for (int c = 0; c <= 10; c++) {
			products.add(Product.builder().id((c + 1L)).name("Product " + (c + 1L)).build());
		}
	}

	@GetMapping(value = "/products")
	public List<Product> findAll() {
		return this.products;
	}

	@GetMapping(value = "/products/{productId}")
	public Product findById(@PathVariable("productId") Long productId) {
		for (Product prod : this.products) {
			if (prod.getId().longValue() == productId.longValue()) {
				return prod;
			}
		}
		return null;
	}

	@PostMapping(value = "/products")
	public Product create(@RequestBody Product product) {
		this.products.add(product);
		return product;
	}

	@PutMapping(value = "/products")
	public Product update(@RequestBody Product product) {
		for (Product prod : this.products) {
			if (prod.getId().longValue() == product.getId().longValue()) {
				prod.setName(product.getName());
				return prod;
			}
		}
		throw new RuntimeException("No existe el producto");
	}

	@DeleteMapping(value = "/products/{productId}")
	public void delete(@PathVariable("productId") Long productId) {
		Product deleteProduct = null;
		for (Product prod : this.products) {
			if (prod.getId().longValue() == productId.longValue()) {
				deleteProduct = prod;
				break;
			}
		}
		if (deleteProduct == null)
			throw new RuntimeException("No existe el producto");
		this.products.remove(deleteProduct);

	}

}
