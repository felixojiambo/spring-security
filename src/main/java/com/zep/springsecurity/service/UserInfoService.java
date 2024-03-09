package com.zep.springsecurity.service;

import com.zep.springsecurity.entity.User;
import com.zep.springsecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
       @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo= userInfoRepository.findByName((username));
           return userInfo.map(UserInfoDetails::new)
                   .orElseThrow(()->new UsernameNotFoundException("User not found"+username));
    }
    public String addUser(User user){
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           userInfoRepository.save(user);
           return  "User added succesfully";
    }
    public List<User> getAllUser(){
           return userInfoRepository.findAll();
    }
    public User getUser(Integer id){
           return userInfoRepository.findById(id).get();
    }
}
