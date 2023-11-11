package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.Entity.RefreshToken;
import com.DataProvider.DataProvider.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepo extends JpaRepository <RefreshToken,Integer>{

    RefreshToken findByToken(String token);
    RefreshToken findByUser(User u);
}
