package com.example.springbootjwtmaven.service;

import com.example.springbootjwtmaven.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String name) ;// check user exist db
    Boolean existsByUsername(String username); // username da co trong db chua khi tao user
    Boolean existsByEmail(String email);
    User save(User user);
}
