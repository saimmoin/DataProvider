package com.DataProvider.DataProvider;

import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Repository.EmployeeRepository;
import com.DataProvider.DataProvider.Scheduling.CroneJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableScheduling
@EnableAspectJAutoProxy

public class DataProviderApplication {



	public static void main(String[] args) {

		SpringApplication.run(DataProviderApplication.class, args);
		System.out.println("Hekllo");




	}

}
