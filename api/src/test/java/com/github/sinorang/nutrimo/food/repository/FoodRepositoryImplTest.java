package com.github.sinorang.nutrimo.food.repository;

import com.github.sinorang.nutrimo.config.TestQuerydslConfig;
import com.github.sinorang.nutrimo.food.entity.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestQuerydslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FoodRepositoryImplTest {

    @Autowired
    FoodRepository foodRepository;

    @Test
    @DisplayName("query가 null/blank면 전체 조회 + 페이징")
    void searchAllWhenBlank() {
        seed("사과", "바나나", "고구마", "아보카도", "닭가슴살");

        Page<Food> page = foodRepository.searchByName(null, PageRequest.of(0, 3));

        Assertions.assertThat(page.getTotalElements()).isEqualTo(5);
        Assertions.assertThat(page.getContent()).hasSize(3);
    }

    @Test
    @DisplayName("containsIgnoreCase로 부분/대소문자 무시 검색")
    void searchIgnoreCase() {
        seed("Apple", "BANANA", "orange", "PINEAPPLE", "사과");

        Page<Food> page = foodRepository.searchByName("apple", PageRequest.of(0, 10));

        Assertions.assertThat(page.getTotalElements()).isEqualTo(2);
        Assertions.assertThat(page.getContent())
                .extracting(Food::getName)
                .allMatch(n -> n.toLowerCase().contains("apple"));
    }

    private void seed(String... names) {
        for (String name : names) {
            Food food = Food.builder()
                    .name(name)
                    .servingSizeGram(100)
                    .kcal(100).carb(10).protein(10).fat(10)
                    .build();
            foodRepository.save(food);
        }
    }
}