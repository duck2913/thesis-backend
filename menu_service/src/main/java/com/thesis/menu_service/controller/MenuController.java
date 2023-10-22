package com.thesis.menu_service.controller;

import com.thesis.menu_service.entity.Dish;
import com.thesis.menu_service.service.MenuService;
import com.thesis.menu_service.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import static com.thesis.menu_service.util.Util.makeDirectoryIfNotExist;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MenuController {
    private static String imageDirectory = System.getProperty("user.dir") + "/menu_service/src/main/resources/static/images/";
    
    private final MenuService menuService;
    
    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        return ResponseEntity.ok(menuService.getAllDishes());
    }
    
    @PostMapping
    public ResponseEntity<String> createNewMenu(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price", required = false) String price,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "image", required = false) MultipartFile file
    ) throws IOException, SQLException {
        // save image to static file
        makeDirectoryIfNotExist(imageDirectory);
        Path fileNamePath = Paths.get(imageDirectory,
                file.getOriginalFilename());
        try {
            Files.write(fileNamePath, file.getBytes());
        } catch (IOException ex) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
        
        
        Dish newDish = Dish.builder()
                .name(name)
                .category(Category.valueOf(category))
                .price(price)
                .imgUrl("/images/" + file.getOriginalFilename())
                .build();
        
        menuService.createNewDish(newDish);
        return ResponseEntity.ok("menu created ." + newDish.toString());
    }
    
    
}