package com.github.sinorang.nutrimo.meal.entity;

import com.github.sinorang.nutrimo.common.repository.BaseEntity;
import com.github.sinorang.nutrimo.common.repository.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meal",
        indexes = @Index(name = "idx_meals_user_date", columnList = "user_id, eaten_at"))
public class Meal extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_meal_user"))
    private User user;

    @Column(name = "eaten_at", nullable = false)
    private LocalDateTime eatenAt;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "text")
    private String memo;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MealItem> items = new ArrayList<>();
}
