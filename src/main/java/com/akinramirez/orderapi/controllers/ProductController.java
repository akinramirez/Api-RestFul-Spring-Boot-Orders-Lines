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

@RestController
public class ProductController {
	@Autowired
	private ProductRepository productRepo;

	private List<Product> products = new ArrayList<>();

	/*public ProductController() {
		for (int c = 0; c <= 10; c++) {
			products.add(Product.builder().id((c + 1L)).name("Product " + (c + 1L)).build());
		}
	}*/

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> findAll() {
		//return this.products;
		List<Product> products = productRepo.findAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<Product> findById(@PathVariable("productId") Long productId) {
		// Dummy
		/*
		 * for (Product prod : this.products) { if (prod.getId().longValue() ==
		 * productId.longValue()) { return prod; } } return null;
		 */
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		return new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT.OK);

	}
	
	

	@PostMapping(value = "/products")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		/*this.products.add(product);
		return product;*/
		Product newProduct = productRepo.save(product);
		return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
	}

	
	@PutMapping(value = "/products")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		/*for (Product prod : this.products) {
			if (prod.getId().longValue() == product.getId().longValue()) {
				prod.setName(product.getName());
				return prod;
			}
		}
		throw new RuntimeException("No existe el producto");*/
		Product existProduct = productRepo.findById(product.getId())
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		existProduct.setName(product.getName());
		existProduct.setPrice(product.getPrice());
		productRepo.save(existProduct);
		return new ResponseEntity<Product>(existProduct, HttpStatus.OK);
		
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
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		productRepo.delete(product);
		return new ResponseEntity(HttpStatus.OK);

	}

}
