package com.github.sinorang.nutrimo.food.service;

import com.github.sinorang.nutrimo.food.dto.response.FoodResponse;
import com.github.sinorang.nutrimo.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public Page<FoodResponse> search(String query, Pageable pageable) {
        return foodRepository.searchByName(query, pageable)
                            .map(FoodResponse::from);
    }
}
