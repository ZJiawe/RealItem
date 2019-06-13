package com.yihu.ehr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class SvrEhrPublicHealth extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SvrEhrPublicHealth.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SvrEhrPublicHealth.class);
	}
}
