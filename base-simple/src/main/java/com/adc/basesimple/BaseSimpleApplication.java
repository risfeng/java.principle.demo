package com.adc.basesimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author risfeng
 */
@SpringBootApplication
public class BaseSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseSimpleApplication.class, args);
        System.out.println("OK");
    }

}
