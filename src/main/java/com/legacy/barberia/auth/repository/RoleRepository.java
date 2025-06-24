package com.legacy.barberia.auth.repository;

import com.legacy.barberia.auth.model.entities.Role;
import com.legacy.barberia.auth.model.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleList name);
}
