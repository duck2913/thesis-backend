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

    private final RedisService redisService;

    public void createNewDish(Dish newDish) {
        //update cache
        var savedEntity = menuRepository.save(newDish);

        List<Dish> allDishes = getAllDishes();
        newDish.setId(savedEntity.getId());
        allDishes.add(newDish);
        redisService.saveData("menu", allDishes);

    }

    public List<Dish> getAllDishes() {
        var cachedMenu = redisService.getData("menu");
        if (cachedMenu == null) {
            List<Dish> menuFromDb = menuRepository.findAll();
            redisService.saveData("menu", menuFromDb);
        }
        return redisService.getData("menu");
    }

    public void deleteDishById(Integer dishId) {
        //update cache
        List<Dish> allDishes = getAllDishes();
        allDishes.stream().filter(dish -> dish.getId() != dishId);

        redisService.saveData("menu", allDishes);

        menuRepository.deleteById(dishId);
    }
}