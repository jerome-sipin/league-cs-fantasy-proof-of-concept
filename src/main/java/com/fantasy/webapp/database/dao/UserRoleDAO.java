package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    List<UserRole> findByRoleName(String roleName);

    UserRole findById(Integer id);

    List<UserRole> findUserRolesByUserId(Integer id);

    //List<UserRole> findByUserId(String userId);

}
