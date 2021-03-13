package org.example.deployment.sync.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.String;

@SpringBootApplication(scanBasePackages = "org.example")
public class SyncApplication {

    public static void main(String[] arg) {
        SpringApplication.run(SyncApplication.class, arg);
    }
}
