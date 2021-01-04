package com.akinramirez.orderapi.controllers;

import java.util.*;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akinramirez.orderapi.entity.*;
import com.akinramirez.orderapi.repository.ProductRepository;
import com.akinramirez.orderapi.services.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	private List<Product> products = new ArrayList<>();

	/*
	 * public ProductController() { for (int c = 0; c <= 10; c++) {
	 * products.add(Product.builder().id((c + 1L)).name("Product " + (c +
	 * 1L)).build()); } }
	 */

	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<Product> findById(@PathVariable("productId") Long productId) {
		// Dummy
		/*
		 * for (Product prod : this.products) { if (prod.getId().longValue() ==
		 * productId.longValue()) { return prod; } } return null;
		 */
		Product product = productService.findById(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity delete(@PathVariable("productId") Long productId) {
		/*
		 * Product deleteProduct = null; for (Product prod : this.products) { if
		 * (prod.getId().longValue() == productId.longValue()) { deleteProduct = prod;
		 * break; } } if (deleteProduct == null) throw new
		 * RuntimeException("No existe el producto");
		 * this.products.remove(deleteProduct);
		 */
		productService.delete(productId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> findAll() {
		// return this.products;
		List<Product> products = productService.findAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/products")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		/*
		 * this.products.add(product); return product;
		 */
		Product newProduct = productService.save(product);
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}

	@PutMapping(value = "/products")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		/*
		 * for (Product prod : this.products) { if (prod.getId().longValue() ==
		 * product.getId().longValue()) { prod.setName(product.getName()); return prod;
		 * } } throw new RuntimeException("No existe el producto");
		 */
		Product updateProduct = productService.save(product);
		return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
	}
}
