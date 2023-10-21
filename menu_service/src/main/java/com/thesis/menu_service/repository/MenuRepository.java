package com.thesis.menu_service.repository;

import com.thesis.menu_service.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Dish, Integer> {
}