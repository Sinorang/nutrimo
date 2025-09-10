package com.github.sinorang.nutrimo.meal.entity;

import com.github.sinorang.nutrimo.common.repository.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meal_image",
        indexes = @Index(name = "idx_meal_image_meal", columnList = "meal_id"))
public class MealImage extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_meal_image_meal"))
    private Meal meal;

    @Column(length = 500, nullable = false)
    private String url;

    @Column(name = "extracted_text", columnDefinition = "text")
    private String extractedText;
}
