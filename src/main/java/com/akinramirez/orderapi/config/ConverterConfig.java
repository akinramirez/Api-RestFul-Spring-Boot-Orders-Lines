package com.akinramirez.orderapi.config;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.akinramirez.orderapi.converters.OrderConverter;
import com.akinramirez.orderapi.converters.ProductConverter;
import com.akinramirez.orderapi.converters.UserConverter;

@Configuration
public class ConverterConfig {

	@Value("${config.datetimeformat}")
	private String datetimeFormat;
	
	@Bean
	public ProductConverter getProductConverter() {
		return new ProductConverter();
	}
	
	@Bean
	public OrderConverter getOrderConverter() {
		DateTimeFormatter format  = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm:ss");
		return new OrderConverter(format, getProductConverter(),getUserConverter());
	}
	
	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}
	
}
