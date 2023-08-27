package com.DataProvider.DataProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class DataProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProviderApplication.class, args);
		System.out.println("Hekllo");
	}

}
