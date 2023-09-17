package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.AOP.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface activityLogRepo extends JpaRepository<ActivityLog, Integer> {

}
