package com.DataProvider.DataProvider.DTO;

import com.DataProvider.DataProvider.AOP.ActivityLogType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityLogRequestDTO {
    private String packageName;
    private String methodName;
    private Date visitedAt;
    private String activityLogType;
}
