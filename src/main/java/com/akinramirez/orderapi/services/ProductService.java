package com.akinramirez.orderapi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.akinramirez.orderapi.entity.Product;
import com.akinramirez.orderapi.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;

	public Product findById(Long productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		return product;
	}

	@Transactional
	public void delete(Long productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		productRepo.delete(product);
	}

	public List<Product> findAll() {
		List<Product> products = productRepo.findAll();
		return products;
	}

	@Transactional
	public Product save(Product product) {
		if (product.getId() == null) {
			Product newProduct = productRepo.save(product);
			return newProduct;
		}

		Product existProduct = productRepo.findById(product.getId())
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		existProduct.setName(product.getName());
		existProduct.setPrice(product.getPrice());
		productRepo.save(existProduct);
		return existProduct;
	}

}
