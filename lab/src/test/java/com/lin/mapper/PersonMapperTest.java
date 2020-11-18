package com.lin.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    UserMapper userMapper;
    @Test
    void getPersonByUsername() {
        System.out.println(userMapper.getUserByUsername("AAA"));
    }

    @Test
    void getPersonLike() {
        System.out.println(userMapper.getUserLike("%AAA%"));
    }
}