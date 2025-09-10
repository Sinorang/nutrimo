package com.github.sinorang.nutrimo.goal.repository;

import com.github.sinorang.nutrimo.goal.entity.NutritionGoal;
import com.github.sinorang.nutrimo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NutritionGoalRepository extends JpaRepository<NutritionGoal, Long> {
    Optional<NutritionGoal> findByUser(User user);
    boolean existsByUser(User user);
}
