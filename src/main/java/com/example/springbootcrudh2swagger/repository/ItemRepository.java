package com.example.springbootcrudh2swagger.repository;

import com.example.springbootcrudh2swagger.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
