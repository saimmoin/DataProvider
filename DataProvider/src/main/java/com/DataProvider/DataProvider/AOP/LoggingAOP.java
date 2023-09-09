package com.DataProvider.DataProvider.AOP;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAOP {
    @Before("within(com.DataProvider.DataProvider.Controller.EmployeeController)")
    public void before(){
        System.out.println("Successfully");
    }
    @After("within(com.DataProvider.DataProvider.Controller.EmployeeController)")
    public void after(){
        System.out.println("After");
    }
}
