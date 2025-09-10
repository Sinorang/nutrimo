package com.github.sinorang.nutrimo.meal.repository;

import com.github.sinorang.nutrimo.meal.entity.Meal;
import com.github.sinorang.nutrimo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserAndEatenAtBetween(User user, LocalDateTime start, LocalDateTime end);
}
