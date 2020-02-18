package com.hust.software.wishbottle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.hust.software.wishbottle.mapper")
@SpringBootApplication
public class WishbottleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishbottleApplication.class, args);
    }

}
