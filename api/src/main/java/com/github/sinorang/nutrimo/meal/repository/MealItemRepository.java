package com.github.sinorang.nutrimo.meal.repository;

import com.github.sinorang.nutrimo.meal.entity.MealItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealItemRepository extends JpaRepository<MealItem, Long> {
}
