package com.akinramirez.orderapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akinramirez.orderapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
	public Optional<User> findByUsername(String username);
}
