package com.github.sinorang.nutrimo.common.repository.domain.user.repository;

import com.github.sinorang.nutrimo.common.repository.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryBase extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
