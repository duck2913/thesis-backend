package com.thesis.menu_service.entity;

import com.thesis.menu_service.util.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue
    private Integer id;
    
    private Category category;
    
    private String name;
    
    @Column(name = "img_url")
    private String imgUrl;
    
    private String price;
    
}