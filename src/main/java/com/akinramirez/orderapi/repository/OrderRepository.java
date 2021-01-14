package com.akinramirez.orderapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akinramirez.orderapi.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
