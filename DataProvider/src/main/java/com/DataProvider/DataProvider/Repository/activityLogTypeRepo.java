package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.AOP.ActivityLogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface activityLogTypeRepo extends JpaRepository<ActivityLogType, Integer> {
    ActivityLogType findByName(String name);
}
