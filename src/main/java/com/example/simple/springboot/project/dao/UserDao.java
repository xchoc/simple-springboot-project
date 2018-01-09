package com.example.simple.springboot.project.dao;

import com.example.simple.springboot.project.entity.User;

public interface UserDao {

    void save(User user);
    User getByName(String name);
    User getById(String id);
}
