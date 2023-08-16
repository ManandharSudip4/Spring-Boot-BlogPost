package com.mstech.springblogpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBlogpostApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogpostApplication.class, args);
	}

}
