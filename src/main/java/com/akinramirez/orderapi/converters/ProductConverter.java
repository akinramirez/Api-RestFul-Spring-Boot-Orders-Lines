package com.akinramirez.orderapi.converters;

import com.akinramirez.orderapi.dtos.ProductDTO;
import com.akinramirez.orderapi.entity.Product;

public class ProductConverter extends AbstractConverter<Product, ProductDTO>{

	@Override
	public ProductDTO fromEntitys(Product entity) {
		return ProductDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.price(entity.getPrice())
				.build();
	}

	@Override
	public Product fromDTO(ProductDTO dto) {
		return Product.builder()
				.id(dto.getId())
				.name(dto.getName())
				.price(dto.getPrice())
				.build();
	}

}
