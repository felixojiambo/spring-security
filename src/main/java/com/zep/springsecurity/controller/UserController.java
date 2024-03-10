package com.zep.springsecurity.controller;

import com.zep.springsecurity.entity.AuthRequest;
import com.zep.springsecurity.entity.User;
import com.zep.springsecurity.service.JWTService;
import com.zep.springsecurity.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  JWTService jwtService;
  @GetMapping("/welcome")
  public  String welcome(){
      return "Welcome to S&S";
  }
  @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
    return userInfoService.addUser(user);

  }
    @PostMapping("/login")
    public String addUser(@RequestBody AuthRequest authRequest) {
     Authentication authenticate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
if(authenticate.isAuthenticated()){
return jwtService.generateToken(authRequest.getUsername());
}else{throw new UsernameNotFoundException("Invalid user request");
}
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
     return userInfoService.getAllUser();
    }
    @GetMapping("/getUsers/id")
    public User getUsers(@PathVariable Integer id){
        return userInfoService.getUser(id);
    }
    }
