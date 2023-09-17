package com.DataProvider.DataProvider.AOP;
import com.DataProvider.DataProvider.DTO.ActivityLogRequestDTO;
import com.DataProvider.DataProvider.Service.impl.ActivityServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Aspect
public class LoggingAOP {
    @Autowired ActivityServiceImpl activityService;
    @Before("within(com.DataProvider.DataProvider.Controller.EmployeeController)")
    public void before(JoinPoint joinPointt){
        String methodName=joinPointt.getSignature().getName();
        String package_name =   joinPointt.getSignature().getDeclaringType().getName();
        Date date = new Date(System.currentTimeMillis());
        System.out.println("Successfully");
        ActivityLogRequestDTO activityLogRequestDTO = new ActivityLogRequestDTO();
        activityLogRequestDTO.setActivityLogType("API_CALLING");
        activityLogRequestDTO.setPackageName(package_name);
        activityLogRequestDTO.setMethodName(methodName);
        activityLogRequestDTO.setVisitedAt(date);
        activityService.saveAllActivities(activityLogRequestDTO);
    }
    @After("within(com.DataProvider.DataProvider.Controller.EmployeeController)")

    public void after(){
        System.out.println("After");
    }
}
