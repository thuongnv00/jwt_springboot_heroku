package com.example.springbootjwtmaven.service;

import com.example.springbootjwtmaven.model.Role;
import com.example.springbootjwtmaven.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
