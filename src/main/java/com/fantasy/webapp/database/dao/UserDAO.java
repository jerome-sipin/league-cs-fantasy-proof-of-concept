package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    User findById(Integer id);

    User findByEmailIgnoreCase(String email);

    @Query("select  u from User u where u.username = :username")
    List<User> findByUsername(String username);

    // User findByUsername(String username);

}