package com.example.springbootjwtmaven.repository;

import com.example.springbootjwtmaven.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String name) ;// check user exist db
    Boolean existsByUsername(String username); // username da co trong db chua khi tao user
    Boolean existsByEmail(String email);
}
