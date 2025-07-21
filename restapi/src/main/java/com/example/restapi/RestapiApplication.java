package com.example.restapi;

import com.example.restapi.config.DotenvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication {

	static{
		Class<?> loader = DotenvLoader.class;
	}

	public static void main(String[] args) {
		DotenvLoader dotenvLoader = new DotenvLoader();
		SpringApplication.run(RestapiApplication.class, args);
	}

}
