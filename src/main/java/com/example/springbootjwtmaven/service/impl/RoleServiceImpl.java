package com.example.springbootjwtmaven.service.impl;

import com.example.springbootjwtmaven.model.Role;
import com.example.springbootjwtmaven.model.RoleName;
import com.example.springbootjwtmaven.repository.IRoleRepository;
import com.example.springbootjwtmaven.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
