package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    User findById(Integer id);

    User findByUsernameIgnoreCase(String username);

    @Query("select u from User u where u.username = :username")
    List<User> findByUsername(String username);

    List<User> findByUsernameContainingIgnoreCase(String username);


    // User findByUsername(String username);

}
