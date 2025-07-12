package com.vyas.myFirstProject;

import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyFirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}


	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory mongoDatabaseFactory){
		return new MongoTransactionManager(mongoDatabaseFactory);
	}

}
