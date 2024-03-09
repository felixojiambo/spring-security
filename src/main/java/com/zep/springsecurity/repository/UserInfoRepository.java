package com.zep.springsecurity.repository;

import com.zep.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository <User, Integer>{
    Optional<User> findByName(String username);
}
