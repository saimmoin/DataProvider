package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.AOP.ActivityLog;
import com.DataProvider.DataProvider.AOP.ActivityLogType;
import com.DataProvider.DataProvider.DTO.ActivityLogRequestDTO;
import com.DataProvider.DataProvider.Repository.activityLogRepo;
import com.DataProvider.DataProvider.Repository.activityLogTypeRepo;
import com.DataProvider.DataProvider.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired activityLogRepo activityLogRepo;
    @Autowired
    activityLogTypeRepo logTypeRepo;
    @Override
    public void saveAllActivities(ActivityLogRequestDTO activityLogRequestDTO) {
        try{
            ActivityLogType activityLogType = new ActivityLogType();
            activityLogType = logTypeRepo.findByName(activityLogRequestDTO.getActivityLogType());
            ActivityLog activityLog = new ActivityLog();
            activityLog.setActivityLogType(activityLogType);
            activityLog.setMethodName(activityLogRequestDTO.getMethodName());
            activityLog.setPackageName(activityLogRequestDTO.getPackageName());
            activityLog.setStatus(true);
            activityLog.setVisitedAt(activityLogRequestDTO.getVisitedAt());
            activityLogRepo.save(activityLog);
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}
