package com.github.sinorang.nutrimo.meal.entity;

import com.github.sinorang.nutrimo.food.entity.Food;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meal_item",
        indexes = @Index(name = "idx_meal_item_meal", columnList = "meal_id"))
public class MealItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_meal_item_meal"))
    private Meal meal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_meal_item_food"))
    private Food food;

    @Column(nullable = false)
    private Float quantity;

    @Column(nullable = false) private Integer kcal;
    @Column(nullable = false) private Integer carb;
    @Column(nullable = false) private Integer protein;
    @Column(nullable = false) private Integer fat;
}
