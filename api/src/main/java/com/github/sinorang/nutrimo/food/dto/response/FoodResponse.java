package com.github.sinorang.nutrimo.food.dto.response;

import com.github.sinorang.nutrimo.food.entity.Food;

public record FoodResponse(
        Long id,
        String name,
        Integer servingSizeGram,
        Integer kcal, Integer carb, Integer protein, Integer fat,
        String brand, String barcode
) {
    public static FoodResponse from(Food food) {
        return new FoodResponse(
                food.getId(),
                food.getName(),
                food.getServingSizeGram(),
                food.getKcal(),
                food.getCarb(),
                food.getProtein(),
                food.getFat(),
                food.getBrand(),
                food.getBarcode()
        );
    }
}
