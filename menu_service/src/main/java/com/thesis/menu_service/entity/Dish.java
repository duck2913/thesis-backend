package com.thesis.menu_service.entity;

import com.thesis.menu_service.util.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    private String name;
    
    @Column(name = "img_url")
    private String imgUrl;
    
    private String price;
    
}