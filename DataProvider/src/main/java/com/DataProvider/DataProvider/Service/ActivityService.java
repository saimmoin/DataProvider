package com.DataProvider.DataProvider.Service;

import com.DataProvider.DataProvider.DTO.ActivityLogRequestDTO;

public interface ActivityService {
    void saveAllActivities(ActivityLogRequestDTO activityLogRequestDTO);
}
