package org.example.betasolutions;

import org.apache.catalina.core.ApplicationContext;
import org.example.betasolutions.task.TaskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BetaSolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetaSolutionsApplication.class, args);
    }

}
