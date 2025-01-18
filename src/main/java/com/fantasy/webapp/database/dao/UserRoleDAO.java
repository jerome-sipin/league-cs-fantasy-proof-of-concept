package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    List<UserRole> findByRoleName(String roleName);

    UserRole findById(Integer id);

    @Query("select ur from UserRole ur")
    List<UserRole> findAllRoles();

    //List<UserRole> findByUserId(String userId);

}
