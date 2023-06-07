package com.purnabhu.user.repository;

import com.purnabhu.user.entities.ERole;
import com.purnabhu.user.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {
    Optional<Roles> findByRoleName(ERole roleName);
    Optional<Roles> findByRoleId(ERole roleId);
}
