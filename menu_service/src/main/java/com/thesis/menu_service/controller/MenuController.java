package com.thesis.menu_service.controller;

import com.thesis.menu_service.entity.Dish;
import com.thesis.menu_service.repository.MenuRepository;
import com.thesis.menu_service.util.Category;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MenuController {
    private static String imageDirectory = System.getProperty("user.dir") + "/menu_service/src/main/resources/static/images/";
    
    private final MenuRepository repository;
    
    @GetMapping
    public String test1() {
        return "menu service ok";
    }
    
    @PostMapping
    public ResponseEntity<String> createNewMenu(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price", required = false) String price,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "image", required = false) MultipartFile file
    ) throws IOException, SQLException {
        System.out.println(name);
        System.out.println(price);
        System.out.println(category);
        System.out.println(file);

//      save image to static file
        makeDirectoryIfNotExist();
        Path fileNamePath = Paths.get(imageDirectory,
                name.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
        try {
            Files.write(fileNamePath, file.getBytes());
        } catch (IOException ex) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }

//
        Dish newDish = Dish.builder()
                .name(name)
                .category(Category.valueOf(category))
                .price(price)
                .build();

//        repository.save(newDish);
        return ResponseEntity.ok("create menu ok");
    }
    
    private void makeDirectoryIfNotExist() {
        File directory = new File(MenuController.imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}