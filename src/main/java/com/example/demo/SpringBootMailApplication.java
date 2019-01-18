package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Oliver Wang
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class SpringBootMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailApplication.class, args);
	}
}
