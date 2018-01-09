package com.example.simple.springboot.project.integration;

import com.example.simple.springboot.project.dao.UserDao;
import com.example.simple.springboot.project.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getByIdTest(){
        User user = userDao.getById("google-oauth2|102846717547482980745");
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualToIgnoringCase("Daria");
    }
}
