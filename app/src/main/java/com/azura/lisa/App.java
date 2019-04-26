package com.azura.lisa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.adonisle.auth.authen",
        "com.adonisle.auth.service",
        "com.azura.common",
        "com.azura.lisa",
        "com.azura.tutorial",
})
@EnableAutoConfiguration(exclude = JpaRepositoriesAutoConfiguration.class)
@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("Start application");
        SpringApplication.run(App.class, args);
    }

}
