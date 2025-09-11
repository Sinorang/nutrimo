package com.github.sinorang.nutrimo.common.repository.domain.user.repository;

import com.github.sinorang.nutrimo.common.repository.domain.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositoryBase extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
