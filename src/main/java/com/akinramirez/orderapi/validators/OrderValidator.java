package com.akinramirez.orderapi.validators;

import com.akinramirez.orderapi.entity.Order;
import com.akinramirez.orderapi.entity.OrderLine;
import com.akinramirez.orderapi.exceptions.ValidateServiceException;

public class OrderValidator {
	
	public static void save(Order order) {

		if (order.getLines() == null || order.getLines().isEmpty()) {
			throw new ValidateServiceException("Las líneas son requeridas");
		}

		for (OrderLine line : order.getLines()) {
			if (line.getProduct() == null || line.getProduct().getId() == null) {
				throw new ValidateServiceException("El producto es requerido");
			}

			if (line.getQuantity() == null) {
				throw new ValidateServiceException("La cantidad es requerido");
			}
			if (line.getQuantity() < 0) {
				throw new ValidateServiceException("La cantidad es incorrecto");
			}
		}
	}
}