package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface addressRepo extends JpaRepository<Address, Integer> {
}
