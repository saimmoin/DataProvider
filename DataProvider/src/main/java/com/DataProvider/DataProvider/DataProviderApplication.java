package com.DataProvider.DataProvider;

import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootApplication
//@EnableDiscoveryClient

@EnableAspectJAutoProxy

public class DataProviderApplication {

	public static void main(String[] args) {

		SpringApplication.run(DataProviderApplication.class, args);
		System.out.println("Hekllo");



	}

}
