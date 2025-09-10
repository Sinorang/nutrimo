package com.github.sinorang.nutrimo.food.repository;

import com.github.sinorang.nutrimo.food.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodRepositoryCustom {
    Page<Food> searchByName(String query, Pageable pageable);
}
