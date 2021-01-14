package com.akinramirez.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.akinramirez.orderapi.entity.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long>{

}
