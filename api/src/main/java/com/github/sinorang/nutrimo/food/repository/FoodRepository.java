package com.github.sinorang.nutrimo.food.repository;

import com.github.sinorang.nutrimo.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>, FoodRepositoryCustom {
}
