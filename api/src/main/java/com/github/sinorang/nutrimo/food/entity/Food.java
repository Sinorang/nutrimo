package com.github.sinorang.nutrimo.food.entity;

import com.github.sinorang.nutrimo.common.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "food",
        indexes = {
            @Index(name = "idx_foods_name", columnList = "name"),
            @Index(name = "idx_foods_barcode", columnList = "barcode")
        })
public class Food extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(name = "serving_size_gram", nullable = false)
    private Integer servingSizeGram; // 1회 제공량(g)

    // 1회 제공량 기준
    @Column(nullable = false)
    private Integer kcal;
    @Column(nullable = false)
    private Integer carb;
    @Column(nullable = false)
    private Integer protein;
    @Column(nullable = false)
    private Integer fat;

    @Column(length = 100)
    private String brand;

    @Column(length = 64)
    private String barcode;
}
