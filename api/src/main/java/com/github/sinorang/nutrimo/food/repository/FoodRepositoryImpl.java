package com.github.sinorang.nutrimo.food.repository;

import com.github.sinorang.nutrimo.common.repository.support.Querydsl7RepositorySupport;
import com.github.sinorang.nutrimo.food.entity.Food;
import com.github.sinorang.nutrimo.food.entity.QFood;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FoodRepositoryImpl extends Querydsl7RepositorySupport<Food>
        implements FoodRepositoryCustom {

    public FoodRepositoryImpl(EntityManager em, JPAQueryFactory qf) {
        super(Food.class, em, qf);
    }

    @Override
    public Page<Food> searchByName(String query, Pageable pageable) {
        QFood food = QFood.food;

        return applyOffsetPagination(
                pageable,
                queryFactory -> queryFactory.selectFrom(food)
                        .where(query == null || query.isBlank() ? null : food.name.containsIgnoreCase(query))
        );
    }
}
