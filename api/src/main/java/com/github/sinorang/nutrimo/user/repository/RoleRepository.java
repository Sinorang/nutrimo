package com.github.sinorang.nutrimo.user.repository;

import com.github.sinorang.nutrimo.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
