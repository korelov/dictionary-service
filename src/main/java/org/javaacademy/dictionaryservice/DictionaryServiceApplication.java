package org.javaacademy.dictionaryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DictionaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictionaryServiceApplication.class, args);
    }
}
