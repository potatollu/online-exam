package com.ysjt.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@MapperScan("com.ysjt.exam.mapper")
@SpringBootApplication
public class YsjtExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(YsjtExamApplication.class, args);
	}

}
