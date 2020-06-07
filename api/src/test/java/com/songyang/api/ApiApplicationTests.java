package com.songyang.api;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ApiApplicationTests {


    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("wsysni8"));
    }

}
