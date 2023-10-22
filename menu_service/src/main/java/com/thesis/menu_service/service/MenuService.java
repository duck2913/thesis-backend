package com.thesis.menu_service.service;

import com.thesis.menu_service.entity.Dish;
import com.thesis.menu_service.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    
    public void createNewDish(Dish newDish) {
        menuRepository.save(newDish);
    }
    
    public List<Dish> getAllDishes() {
        return menuRepository.findAll();
    }
    
    public void deleteDish(Dish dish) {
        menuRepository.delete(dish);
    }
}