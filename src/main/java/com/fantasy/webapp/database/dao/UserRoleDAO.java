package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    List<UserRole> findByRoleName(String role);

    UserRole findById(Integer id);

    List<UserRole> findByUserId(String userId);

}
