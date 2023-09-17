package com.DataProvider.DataProvider.AOP;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "activity_log")
public class ActivityLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Column(name = "package_name")
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Date getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Date visitedAt) {
        this.visitedAt = visitedAt;
    }

    public ActivityLogType getActivityLogType() {
        return activityLogType;
    }

    public void setActivityLogType(ActivityLogType activityLogType) {
        this.activityLogType = activityLogType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name = "method_name")
    private String methodName;
    @Column(name = "visited_at")
    private Date visitedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_type")
    private ActivityLogType activityLogType;

    private  Boolean status;




}
