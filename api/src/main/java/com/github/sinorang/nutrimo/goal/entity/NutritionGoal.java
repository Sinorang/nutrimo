package com.github.sinorang.nutrimo.goal.entity;

import com.github.sinorang.nutrimo.common.repository.BaseEntity;
import com.github.sinorang.nutrimo.common.repository.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "nutrition_goal",
        uniqueConstraints = @UniqueConstraint(name = "uk_goal_user", columnNames = "user_id"))
public class NutritionGoal extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_goal_user"))
    private User user;

    @Column(name = "target_kcal", nullable = false)
    private Integer targetKcal;

    @Column(name = "target_carb")
    private Integer targetCarb;

    @Column(name = "target_protein")
    private Integer targetProtein;

    @Column(name = "target_fat")
    private Integer targetFat;
}
