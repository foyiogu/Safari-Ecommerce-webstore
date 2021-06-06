package com.decagon.safariwebstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class SafariWebStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafariWebStoreApplication.class, args);
    }

}
