package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getUserByEnabledTrue();
    User findAllByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
