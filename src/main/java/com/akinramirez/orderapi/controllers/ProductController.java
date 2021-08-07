package com.akinramirez.orderapi.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akinramirez.orderapi.converters.ProductConverter;
import com.akinramirez.orderapi.dtos.ProductDTO;
import com.akinramirez.orderapi.entity.*;
import com.akinramirez.orderapi.repository.ProductRepository;
import com.akinramirez.orderapi.services.ProductService;
import com.akinramirez.orderapi.utils.WrapperResponse;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductConverter converter = new ProductConverter()	;

	private List<Product> products = new ArrayList<>();

	/*
	 * public ProductController() { for (int c = 0; c <= 10; c++) {
	 * products.add(Product.builder().id((c + 1L)).name("Product " + (c +
	 * 1L)).build()); } }
	 */

	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<WrapperResponse<ProductDTO>> findById(@PathVariable("productId") Long productId) {
		// Dummy
		/*
		 * for (Product prod : this.products) { if (prod.getId().longValue() ==
		 * productId.longValue()) { return prod; } } return null;
		 */
		Product product = productService.findById(productId);
		ProductDTO productDTO = converter.fromEntity(product);
		return new WrapperResponse<ProductDTO>(true,"success",productDTO)
				.createResponse(HttpStatus.OK); 
	}

	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity<?> delete(@PathVariable("productId") Long productId) {
		/*
		 * Product deleteProduct = null; for (Product prod : this.products) { if
		 * (prod.getId().longValue() == productId.longValue()) { deleteProduct = prod;
		 * break; } } if (deleteProduct == null) throw new
		 * RuntimeException("No existe el producto");
		 * this.products.remove(deleteProduct);
		 */
		productService.delete(productId);
		return new WrapperResponse(true,"success",null)
				.createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> findAll(
			@RequestParam(value="pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value="pageSize", required = false, defaultValue = "5") int pageSize
			) {
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		// return this.products;
		List<Product> products = productService.findAll(page);
		List<ProductDTO> dtoProducts = converter.fromEntity(products);
		return new WrapperResponse(true,"success",dtoProducts)
				.createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/products")
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
		/*
		 * this.products.add(product); return product;
		 */
		Product newProduct = productService.save(converter.fromDTO(product));		
		ProductDTO productDTO = converter.fromEntity(newProduct);		
		return new WrapperResponse(true,"success",productDTO)
				.createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/products")
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product) {
		/*
		 * for (Product prod : this.products) { if (prod.getId().longValue() ==
		 * product.getId().longValue()) { prod.setName(product.getName()); return prod;
		 * } } throw new RuntimeException("No existe el producto");
		 */
		Product updateProduct = productService.save(converter.fromDTO(product));		
		ProductDTO productDTO = converter.fromEntity(updateProduct);
		return new WrapperResponse(true,"success",productDTO)
				.createResponse(HttpStatus.OK);
	}
}
