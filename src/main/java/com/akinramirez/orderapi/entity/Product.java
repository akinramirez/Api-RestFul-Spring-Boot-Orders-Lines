package com.akinramirez.orderapi.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Generan constructores
@NoArgsConstructor
@AllArgsConstructor
//Getter / Setter
@Getter
@Setter
//Crea entidades de forma mas sencillas
@Builder
@Entity
@Table(name="PRODUCTS")

public class Product {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME", nullable = false, length = 100)
	private String name;
	
	@Column(name="PRICE", nullable = false)
	private Double price;
	
	//Se reemplaza usando la libreria lombok
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
	
}
