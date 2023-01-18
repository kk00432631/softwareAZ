package com.hcl.utilityFactory.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hcl.utilityFactory")
public class SagUpgradeFactoryApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SagUpgradeFactoryApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SagUpgradeFactoryApplication.class, args);
	}

}
