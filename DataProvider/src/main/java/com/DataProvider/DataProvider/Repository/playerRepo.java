package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface playerRepo extends JpaRepository<Player, Integer> {
}
